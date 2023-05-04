package org.davidfabio.ui;

import org.davidfabio.PolygonWars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.davidfabio.game.Score;

import java.util.ArrayList;

public class HighscoreScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    private Table mainTable;
    private ArrayList<Score> scores;

    public HighscoreScreen(ArrayList<Score> scores) {
        this.scores = scores;
    }

    @Override
    public void show() {
        this.viewport = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.stage = new Stage(this.viewport);

        this.mainTable = new Table();
        this.mainTable.setFillParent(true);
        this.stage.addActor(this.mainTable);
        float columnWidth = Gdx.graphics.getWidth() * 0.4f / 4;

        UIBuilder.loadSkin();
        UIBuilder.addTitleLabel(this.mainTable,"HIGHSCORES",true);
        Table highScoresTable = new Table();
        UIBuilder.addSubtitleLabel(highScoresTable,"Score",columnWidth,true);
        UIBuilder.addSubtitleLabel(highScoresTable,"Username",columnWidth,false);
        UIBuilder.addSubtitleLabel(highScoresTable,"Pickups",columnWidth,false);
        UIBuilder.addSubtitleLabel(highScoresTable,"Time played",columnWidth,false);
        for(Score score : getHighscores()) {
            long durationInSeconds = score.getDuration() / 1000;
            UIBuilder.addLabel(highScoresTable,"" + score.getPoints(),columnWidth,true);
            UIBuilder.addLabel(highScoresTable,score.getUsername(),columnWidth,false);
            UIBuilder.addLabel(highScoresTable,String.format("%d",score.getPickups()),columnWidth,false);
            UIBuilder.addLabel(highScoresTable,String.format("%02d:%02d", durationInSeconds / 60, (durationInSeconds % 60)),columnWidth,false);
        }
        this.mainTable.row();
        this.mainTable.add(highScoresTable).minWidth(Gdx.graphics.getWidth()*0.4f).padBottom(10);
        UIBuilder.addButton(this.mainTable,"Back",true,new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((PolygonWars)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(scores));
            }
        });

        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act();
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private ArrayList<Score> getHighscores() {
        ArrayList<Score> highscores = new ArrayList<>();
        highscores.addAll(this.scores);
        highscores.sort(Score::compareTo);
        if (highscores.size() > 5) {
            for(int i = 5; i < highscores.size(); i++) {
                highscores.remove(i);
            }
        }
        return highscores;
    }
}
