import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import javafx.scene.control.Dialog
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.FileChooser


internal class View1(
    private val model: Model,
    controller: Controller
    ) : GridPane(), IView {
  //  ImageView(Image("File:/CSS/nova_hra.png"))
    private val buttonSelect = Button( "" )
    private val bCircle = Button("")
    private val bRec = Button("")
    private val bLine = Button("")
    private val bErase = Button("")
    private val fillin = Button("")
    private val thinline = Button("")
    private val midline = Button("")
    private val thickline = Button("")
    private val line1 = Button("")
    private val line2 = Button("")
    private val line3 = Button("")
    private val fillCo1 = Button("fill")
    private val lineCo1 = Button("line")
    private val lineCo = ColorPicker(Color.BLACK)
    private val fillCo = ColorPicker(Color.GREY)
    val fillDia = Dialog<Rectangle>()
    val lineDia = Dialog<Rectangle>()
    val fillPane = GridPane()
    val linePane = GridPane()





    // When notified by the model that things have changed,
    // update to display the new value
    override fun updateView() {
        println("View: updateView")
    }

    init {
        buttonSelect.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/cursor.png", 20.0, 20.0, true, true)))
        bCircle.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/dry-clean.png", 20.0, 20.0, true, true)))
        bRec.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/rectangle.png", 20.0, 20.0, true, true)))
        bLine.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/diagonal-line.png", 20.0, 20.0, true, true)))
        bErase.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/eraser.png", 20.0, 20.0, true, true)))
        fillin.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/paint-bucket.png", 20.0, 20.0, true, true)))
        thinline.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/thinline.png", 20.0, 20.0, true, true)))
         midline.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/midline.png", 20.0, 20.0, true, true)))
         thickline.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/thickline.png", 20.0, 20.0, true, true)))
         line1.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/line.png", 20.0, 20.0, true, true)))
         line2.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/line2.png", 20.0, 20.0, true, true)))
         line3.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/line3.png", 20.0, 20.0, true, true)))
        //fillCo.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/paint-bucket.png", 20.0, 20.0, true, true)))
       // lineCo.setGraphic( ImageView(javafx.scene.image.Image("${System.getProperty("user.dir")}/test/button/paint-bucket.png", 20.0, 20.0, true, true)))
        // setup the view (i.e. group+widget)
        //fillCo.setStyle("-fx-background-color: #4343f3; -fx-border-color: BLACK; -fx-border-width: 1px")
        //lineCo.setStyle("-fx-background-color: #afdcaf; -fx-border-color: BLACK; -fx-border-width: 1px")
        fillCo.setMaxWidth(30.0)
        lineCo.setMaxWidth(30.0)
        this.setStyle("-fx-border-color: grey")
//        lineCo.setStyle("-fx-text-fill: stroke")
//        fillCo.setStyle("-fx-text-fill: fill")
//        lineDia.setTitle("Choose your color")
//        fillDia.setTitle("Choose your color")
//        val btok = Button("OK")
//        val color = ColorPicker(Color.BLACK)
//        fillPane.add(color, 0, 0)
//        fillPane.add(btok, 0, 1)
//        fillPane.add(color, 0, 0)
//        fillPane.add(btok, 0, 1)









        // register the controller as a handler for this view
        buttonSelect.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setTool(0)
        }
        bErase.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setTool(1)
        }
        bCircle.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setTool(2)
        }
        bLine.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setTool(3)
        }
        bRec.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setTool(4)
        }
        fillin.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setTool(5)
        }
        line1.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setStyleline(0.0)
        }
        line2.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setStyleline(24.0)
        }
        line3.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setStyleline(10.0)
        }
        thickline.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setThick(6.0)
        }
        midline.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setThick(3.0)
        }
        thinline.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            controller.clearselect()
            controller.setThick(1.0)
        }



        lineCo.setOnAction {
            print("lick linco\n")
            controller.setlineCo(lineCo)
        }
        fillCo.setOnAction{
            print("click fillco\n")
            controller.setfillCo(fillCo)
        }
        controller.setLineFill(lineCo, fillCo)
        controller.setstyleP(thinline, midline,thickline, line1 , line2, line3)

        // add button widget to the pane
        this.add(buttonSelect, 0, 0)
        this.add(bErase, 1, 0)
        this.add(bCircle, 0, 1)
        this.add(bLine, 1, 1)
        this.add(bRec, 0 , 2)
        this.add(fillin, 1 , 2)
        this.add(lineCo, 0 , 10)
        this.add(fillCo, 1 , 10)
        this.add(Text("stroke"), 0, 9)
        this.add(Text("fill"), 1, 9)
        this.add(thinline, 0, 12)
        this.add(midline, 1, 12)
        this.add(thickline, 2, 12)
        this.add(line1, 0, 14)
        this.add(line2, 1, 14)
        this.add(line3, 2, 14)
        this.addEventHandler(KeyEvent.ANY) { e ->
            if (e.getCode().equals(KeyCode.DELETE) || e.getCode().equals(KeyCode.BACK_SPACE)) {
                print("wuuuuuuuuuu\n")
                controller.delete()
                //print("delete\n")
            }
        }
        val keyCombineCopy = KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN)
        this.addEventHandler(KeyEvent.ANY) { e ->
            if (keyCombineCopy.match(e)) {
            }
        }


        this.vgap = 10.0
        this.hgap = 10.0

        // register with the model when we're ready to start receiving data
        model.addView(this)
    }
}