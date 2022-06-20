import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import java.io.File
import javafx.scene.layout.StackPane
import javafx.scene.paint.*
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import javafx.event.ActionEvent
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.shape.Path
import javafx.stage.FileChooser
import java.io.InputStream
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths
import java.io.IOException
import javafx.stage.FileChooser.ExtensionFilter
import kotlin.math.abs

class Main : Application() {
    override fun start(stage: Stage) {

        // CREATE WIDGETS TO DISPLAY
        // menubar & toolbar
        val menuBar = MenuBar()
        val fileMenu = Menu("File")
        val helpMenu = Menu("Help")
        val editMenu = Menu("Edit")

        val menuAbout = MenuItem("About")
        val menuNew = MenuItem("New")
        val menuLoad = MenuItem("Load")
        val menuSave = MenuItem("Save")
        val menuQuit = MenuItem("Quit")
        val menuCopy = MenuItem("Copy")
        val menuPaste = MenuItem("Paste")
        val menuCut = MenuItem("Cut")
        menuQuit.setOnAction { Platform.exit() }

        editMenu.items.addAll(menuCopy, menuPaste, menuCut)
        fileMenu.items.addAll(menuNew, menuLoad, menuSave, menuQuit)
        helpMenu.items.addAll(menuAbout)
        menuBar.menus.addAll(fileMenu, editMenu, helpMenu)
        // stack menu and toolbar in the top region
        val vbox = VBox(menuBar)
        var vboxTools = VBox()
        var canvasMiddle = Canvas(300.0, 400.0)


        // panel on left side, need to replace with a tree
        val pane = ScrollPane()
        pane.prefWidth = 100.0
        pane.prefHeight = 600.0
        pane.style = "-fx-background-color: black;"
        var fileList = ListView<String>()

        // create and initialize the Model to hold our counter
        val model = Model()

        // create the Controller, and tell it about Model
        // the controller will handle input and pass requests to the model
        val controller = Controller(model)
        var scrollPane = ScrollPane()


        val viewTools = View1(model, controller)
        val viewBoard = View2(model, controller)
        val fillTools = View3(model, controller)
        val leftVbox = HBox(viewTools, fillTools)
        //vboxTools.children.add(viewTools) // top-viewed
        scrollPane.setContent(viewBoard)


        //universal path data
        var selectedPath = "${System.getProperty("user.dir")}/test/"
        var absPath = "${System.getProperty("user.dir")}/test/"


        // SETUP LAYOUT
        var centerText = Text("no file picked")
        val notreadableText = Text("File cann t be read")
        val notSupportText = Text("File cannot be read")
        val border = BorderPane()
//        border.setMaxSize(640.0, 480.0)
//        border.setMinSize(1920.0, 1440.0)
        border.top = vbox
        border.left = viewTools
        border.center = scrollPane
        border.left.setStyle("-fx-background-color: grey")


        var filechoose = FileChooser()
        var file: File
        filechoose.extensionFilters.add(ExtensionFilter("txt", "*.txt"))
        menuSave.setOnAction { e ->
            model.clearselect()
            file  = filechoose.showSaveDialog(stage)
            controller.save(file)
        }
        menuLoad.setOnAction { e ->
            file  = filechoose.showOpenDialog(stage)
            controller.load(file)
        }
        menuCopy.setOnAction { e ->
            controller.copy()
        }
        menuPaste.setOnAction { e ->
            controller.paste()
        }
        menuCut.setOnAction { e ->
            model.clearselect2()
            controller.cut()
        }
        stage.addEventHandler(KeyEvent.ANY) {e ->
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                print("ssssss\n")
                model.clearselect()
            }
            if (e.getCode().equals(KeyCode.DELETE) || e.getCode().equals(KeyCode.BACK_SPACE)) {
                print("wuuuuuuuuuu\n")
                controller.delete()
                //print("delete\n")
            }
        }
        menuAbout.setOnAction { e ->
            val alertCheck = Alert(Alert.AlertType.CONFIRMATION)
            alertCheck.setTitle("About dialog")
            alertCheck.setContentText("Application name: UW-photoshop\nStudent Name: Chris He\nWatID: 20845382")
            var answer = alertCheck.showAndWait()
        }
        menuNew.setOnAction {
            model.reset()
            model.simpleRedraw(model.gcP)
        }


        //  border.bottom = HBox(Text(absPath))


        var ifShowHidden = false

//        //implement button delete
//        var updateButton = Button("updateButton")
//        menuSave.setOnAction {
//            val alertCheck = Alert(Alert.AlertType.CONFIRMATION)
//            alertCheck.setTitle("Confirmation Dialog")
//            alertCheck.setContentText("Are you sure to delete?")
//            var answer = alertCheck.showAndWait()
//
//            if (answer.get() == ButtonType.OK) {
//                if (!(selectedPath == absPath)) {
//                    //print("deleting: ${selectedPath} \n")
//                    File(selectedPath).inputStream().close()
//                    val success = File(selectedPath).delete()
//                    //print("${success}\n")
//                    updateButton.fire()
//                }
//                else {
//                    selectedPath = absPath
//                    updateButton.fire()
//                }
//            }
//        }

//        //implement button rename
//        var nameAsking = TextInputDialog("")
//        nameAsking.setHeaderText("enter the new name")
//        var newName = "errorName"
//        menuLoad.setOnAction {
//            if (selectedPath == absPath) {
//                val alertCheck = Alert(Alert.AlertType.CONFIRMATION)
//                alertCheck.setTitle("ERROR Dialog")
//                alertCheck.setContentText("ERROR: please select an item")
//                alertCheck.showAndWait()
//            } else {
//                val result = nameAsking.showAndWait()
//                if (result.isPresent) {
//                    newName = nameAsking.getEditor().getText()
//                    print("${File(selectedPath).name} ------> ${newName}\n")
//                    var ifRenameSuccess = File(selectedPath).renameTo(File(absPath + newName))
//                    if (!ifRenameSuccess) {
//                        val alertCheck = Alert(Alert.AlertType.CONFIRMATION)
//                        alertCheck.setTitle("ERROR Dialog")
//                        alertCheck.setContentText("ERROR: the new name is invalid")
//                        alertCheck.showAndWait()
//                        updateButton.fire()
//                    } else {
//                        selectedPath = absPath
//                        updateButton.fire()
//                    }
//                }
//            }
//        }













        //end of implementing button click features*******************************************************************************



        // CREATE AND SHOW SCENE
        val scene = Scene(border, 800.0, 600.0)
//        val sceneHandler1keyBack =
//            EventHandler { e: KeyEvent ->
//                if (e.getCode().equals(KeyCode.DELETE) || e.getCode().equals(KeyCode.BACK_SPACE)) {
//                    menuLoad.fire()
//                    //print("delete\n")
//                }
//            }
//        val sceneHandler1keyEnter =
//            EventHandler { e: KeyEvent ->
//                if (e.getCode().equals(KeyCode.ENTER)) {
//                    menuLoad.fire()
//                }
//            }
//        scene.addEventHandler(KeyEvent.ANY , sceneHandler1keyBack)
//        scene.addEventHandler(KeyEvent.ANY , sceneHandler1keyEnter)
        stage.minHeight = 480.0
        stage.maxHeight = 1440.0
        stage.minWidth = 640.0
        stage.maxWidth = 1920.0
        stage.scene = scene
        stage.title = "A2"
        stage.show()


    }
}