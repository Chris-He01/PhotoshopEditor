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

internal class View3(
    private val model: Model,
    controller: Controller
    ) : Pane(), IView{

    private val text = TextArea("")

    // When notified by the model that things have changed,
    // update to display the new value
    override fun updateView() {
        println("View2: updateView")

        // display an 'X' for each counter value
//        val s = StringBuilder()
//        for (i in 0 until model.counterValue) s.append("X")
//        text.text = s.toString()
    }

    init {
        // set textfield properties
//        text.isWrapText = true
//        text.isEditable = false

        val root = Group()
        val scene = Scene(root, 800.0, 800.0, Color.WHITE)

        val canvas = Canvas(800.0, 800.0)
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

        root.children.add(canvas)

        this.children.add(root)

        // add label widget to the pane

        // register with the model when we're ready to start receiving data
        model.addView(this)

    }
}