package src;

import javafx.scene.control.MenuBar;

public class TopBar extends MenuBar{
    ClickableMenu help;
    ClickableMenu about;
    public TopBar() {
        super();
        this.setMinHeight(25);
        this.setMaxHeight(25);
        populate();
    }

    private void populate() {
        help = new ClickableMenu("Help");
        help.setOnAction(e -> {
            runNow(() -> System.out.println("Help Pressed"));
        });




        about = new ClickableMenu("About");
        about.setOnAction(e -> {
            runNow(() -> {
                System.out.println("About Pressed");
            });
        });



        this.getMenus().addAll(help, about);
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
