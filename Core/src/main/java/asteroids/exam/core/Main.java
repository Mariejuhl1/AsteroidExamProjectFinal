package asteroids.exam.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);                         // start JavaFX
    }

    @Override
    public void start(Stage window) throws Exception {
        // initialize Spring context
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        // print all beans for debugging
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = ctx.getBean(Game.class);       // get Game bean
        game.start(window);                        // set up game window
        game.render();                             // begin game loop
    }
}
