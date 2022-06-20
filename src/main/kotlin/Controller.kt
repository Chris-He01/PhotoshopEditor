import javafx.fxml.FXMLLoader
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import java.io.File
import java.util.DoubleSummaryStatistics
import javafx.stage.FileChooser
import java.io.EOFException
import java.util.StringTokenizer
import javafx.scene.paint.Color


class Controller(
    var model: Model
    ) {

    fun delete() {
        model.delete()
    }

    fun load(file:File) {
        file.bufferedReader().use {e ->
            model.reset()
//            var line = "emm"
//            var ifsuccess = false
//            ifsuccess = true
//            try {
//                line = e.readLine()
//            } catch (e: EOFException) {
//                ifsuccess = false
//            }
            while(true) {
                var line = e.readLine()
                if (line == null) break
                var st = StringTokenizer(line, ",")
                model.type2 = st.nextToken().toInt()
                model.xl = st.nextToken().toDouble()
                model.yl = st.nextToken().toDouble()
                model.xr = st.nextToken().toDouble()
                model.yr = st.nextToken().toDouble()
                model.r1 = st.nextToken().toDouble()
                model.stWidth = st.nextToken().toDouble()
                model.stDash = st.nextToken().toDouble()
                model.stStroke =  Color.web(st.nextToken())
                model.stFill =  Color.web(st.nextToken())
                model.updateCp()
                model.addCp()

            }
            model.simpleRedraw(model.gcP)
        }
    }

    fun save(file: File) {
        file.bufferedWriter().use {e ->
            for(i in 0 .. model.listSize-1  ){
                e.write("${model.displayList[i].type},${model.displayList[i].xl},${model.displayList[i].yl},${model.displayList[i].xr},${model.displayList[i].yr}," +
                        "${model.displayList[i].r1},${model.displayList[i].strokeWidth},${model.displayList[i].strokeDash},${model.displayList[i].stroke},${model.displayList[i].fill}\n")
            }
        }
    }

    fun copy() {
        model.copy()
    }

    fun paste() {
        model.paste()
    }

    fun cut() {
        model.cut()
    }

    fun clearselect() {
        if (model.tempIndex >= 0) {
            model.displayList[model.tempIndex].strokeWidth = model.displayList[model.tempIndex].strokeWidth - 3
            model.tempIndex =  -1
        }
        model.simpleRedraw(model.gcP)
    }

    fun setTool(x: Int) {
        print("changing shape ${x}\n")
        model.theShape = x
    }
    fun setStyleline(x: Double) {
        model.clearStyleB()
        if (x== 24.0) model.line2.setStyle("-fx-background-color: DarkGrey; ")
        else if  (x == 10.0) model.line3.setStyle("-fx-background-color: DarkGrey; ")
        else if  (x== 0.0) model.line1.setStyle("-fx-background-color: DarkGrey; ")

        model.lineStyle = x
    }

    fun setThick(x: Double) {
        model.clearStyleA()
        print("tryyyyyy\n")
        if (x == 1.0) model.thinline.setStyle("-fx-background-color: DarkGrey; ")
        else if  (x == 3.0) model.midline.setStyle("-fx-background-color: DarkGrey; ")
        else if  (x == 6.0) model.thickline.setStyle("-fx-background-color: DarkGrey; ")

        model.lineThick = x
    }

    fun handle() {
        println("Controller: changing Model (actionPerformed)")
        model.incrementCounter()
    }

    fun setstyleP(thinline: Button, midline: Button,thickline: Button, line1: Button , line2: Button, line3: Button) {
        model.thinline = thinline
        model.midline = midline
        model.thickline = thickline
        model.line1 = line1
        model.line2 = line2
        model.line3 = line3
    }

    fun setfillCo(bt: ColorPicker) {
        model.setfillCo(bt)
    }
    fun setlineCo(bt: ColorPicker) {
        model.setlineCo(bt)
    }

    fun setGc(gc: GraphicsContext) {
        model.gcP = gc
    }

    fun setLineFill(line: ColorPicker, fill: ColorPicker) {
        model.tsfillCo = fill
        model.tslineCo = line
    }



//    fun setPicker(fill: &ColorPicker, line: &ColorPicker) {
//
//    }

    fun stClick(x: Double, y: Double, gc: GraphicsContext) {
        model.stClick(x, y, gc)

    }

    fun stPress(x: Double, y: Double, gc: GraphicsContext) {
        model.stPress(x, y, gc)

    }

    fun stDrag(x: Double, y: Double, gc: GraphicsContext) {
        model.stDrag(x, y, gc)
    }
    fun stMove(x: Double, y: Double, gc: GraphicsContext) {
       // model.stMove(x, y, gc)
    }

    fun stRelease(x: Double, y: Double, gc: GraphicsContext) {
        model.stRelease(x, y, gc)
    }

}