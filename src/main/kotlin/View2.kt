import javafx.scene.control.TextArea
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javax.swing.GroupLayout
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.control.ScrollPane

internal class View2(
    private val model: Model,
    controller: Controller
    ) : Pane(), IView{

    private val text = TextArea("")

    // When notified by the model that things have changed,
    // update to display the new value
    override fun updateView() {
        println("View2: updateView")
    }

    init {

        //setting the main drawing page
        val root = Group()
        val scene = Scene(root, 800.0, 800.0, Color.WHITE)

        val canvas = Canvas(800.0, 600.0)
        val gc = canvas.graphicsContext2D


        // register the controller as a handler for this view\
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED) { e ->
            print("press\n")
            controller.stPress(e.x, e.y, gc)
        }
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED) { e ->
            //print("clicked\n")
            controller.stMove(e.x, e.y, gc)
        }
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED) { e ->
            print("DRAG\n")
            controller.stDrag(e.x, e.y, gc)
        }
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED) { e ->
            print("released\n")
            controller.stRelease(e.x, e.y, gc)
        }

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED) { e ->
            print("released\n")
            controller.stClick(e.x, e.y, gc)
        }
        canvas.addEventHandler(KeyEvent.ANY) {e ->
            if (e.getCode().equals(KeyCode.DELETE) || e.getCode().equals(KeyCode.BACK_SPACE)) {
                print("wuuuuuuuuuu\n")
                controller.delete()
                //print("delete\n")
            }
        }

        //init the vanvas and gc
        controller.setGc(gc)
        root.children.add(canvas)
        var scrollPane = ScrollPane()
        scrollPane.setContent(root)




        // add label scrollPane to the view2
        this.children.add(scrollPane)



        // register with the model when we're ready to start receiving data
        model.addView(this)

    }
}