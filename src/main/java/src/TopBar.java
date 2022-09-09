package src;

import javafx.scene.control.MenuBar;

public class TopBar extends MenuBar{
    ClickableMenu options;
    ClickableMenu help;
    public TopBar() {
        super();
        this.setMinHeight(25);
        this.setMaxHeight(25);
        populate();
    }

    private void populate() {
        options = new ClickableMenu("Options");
        options.setOnAction(e -> {
            runNow(() -> System.out.println("Options Pressed"));
        });




        help = new ClickableMenu("Help");
        help.setOnAction(e -> {
            runNow(() -> {
                System.out.println("Help Pressed");
            });
        });



        this.getMenus().addAll(options, help);
    }

    /**
     * Multithreading
     * @param target
     */
    public static void runNow(Runnable target) {
        Thread thread = new Thread(target);
        thread.setDaemon(true);
        thread.start();
    }
}
