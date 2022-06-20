import javafx.scene.canvas.GraphicsContext
import javafx.geometry.Point2D
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import javafx.scene.paint.Color


private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val point1 = Point2D(lat1, lon1)
    val point2 = Point2D(lat2, lon2)
    return point1.distance(point2)
}

class Model {

    //region View Management
    var canvasP = Canvas(640.0, 480.0)
    var gcP = canvasP.graphicsContext2D
    var tslineCo = ColorPicker(Color.BLUE)
    var tsfillCo = ColorPicker(Color.BLACK)
    var thinline = Button("")
    var midline = Button("")
    var thickline = Button("")
    var line1 = Button("")
    var line2 = Button("")
    var line3 = Button("")


    // all views of this model
    private val views: ArrayList<IView> = ArrayList()
    private var canvasH = 640.0
    private var canvasW = 480.0
    private var pressX = 0.0
    private var pressY = 0.0
    private var lastx = 0.0
    private var lasty = 0.0
    private var ifPress = false
    val displayList = mutableListOf<Shape>()
    var listSize = 0
    var lineStyle = 0.0
    var lineThick = 1.0
    var tempIndex = -1
    var copyIndex = -1
    //var lastIndex = -1



    var theShape = -1;                  //0: invalid shape; 0erase 1circle 2rec 3line1
    private var tempShapeLine = Line(0.0 , 0.0, 0.0, 0.0)
    private var tempShapeCir = Circle(0.0,0.0,0.0)
    private var tempShapeRec = Rectangle(0.0,0.0,0.0, 0.0)
     var type2 = -1
    private var ifcopyed = false
    private var cpLine = Line(0.0 , 0.0, 0.0, 0.0)
    private var cpCir = Circle(0.0,0.0,0.0)
    private var cpRec = Rectangle(0.0,0.0,0.0, 0.0)
     var xl =0.0
     var yl =0.0
     var xr =0.0
     var yr =0.0
     var r1 =0.0
    var stDash = 0.0
    var stWidth = 0.0
    var stFill = Color.GREY
    var stStroke = Color.BLACK




    var fillCo = Color.GREY
    var lineCo = Color.BLACK

    fun reset() {
        ifPress = false
        listSize = 0
        theShape = -1
        tempIndex = -1
        displayList.clear()
    }

    // method that the views can use to register themselves with the Model
    // once added, they are told to update and get state from the Model
    fun addView(view: IView) {
        views.add(view)
        view.updateView()
    }

    fun storeCp2() {
        if (type2 == 2) {
            xl = displayList[tempIndex].xl
            yl = displayList[tempIndex].yl
            xr = displayList[tempIndex].xr
            yr = displayList[tempIndex].yr
            stDash = displayList[tempIndex].strokeDash
            stWidth = displayList[tempIndex].strokeWidth
            stFill = displayList[tempIndex].fill
            stStroke =  displayList[tempIndex].stroke
            cpLine = Line(displayList[tempIndex].xl, displayList[tempIndex].yl, displayList[tempIndex].xr, displayList[tempIndex].yr)
            cpLine.strokeDash = displayList[tempIndex].strokeDash
            cpLine.strokeWidth = displayList[tempIndex].strokeWidth
            cpLine.fill = displayList[tempIndex].fill
            cpLine.stroke = displayList[tempIndex].stroke
        }
        else if (type2 == 1) {
            xl = displayList[tempIndex].xl
            yl = displayList[tempIndex].yl
            r1 = displayList[tempIndex].r1
            stDash = displayList[tempIndex].strokeDash
            stWidth = displayList[tempIndex].strokeWidth
            stFill = displayList[tempIndex].fill
            stStroke =  displayList[tempIndex].stroke
            cpCir = Circle(displayList[tempIndex].xl, displayList[tempIndex].yl, displayList[tempIndex].r1)
            cpCir.strokeDash = displayList[tempIndex].strokeDash
            cpCir.strokeWidth = displayList[tempIndex].strokeWidth
            cpCir.fill = displayList[tempIndex].fill
            cpCir.stroke = displayList[tempIndex].stroke
        }
        else if (type2 == 3) {
            xl = displayList[tempIndex].xl
            yl = displayList[tempIndex].yl
            xr = displayList[tempIndex].xr
            yr = displayList[tempIndex].yr
            stDash = displayList[tempIndex].strokeDash
            stWidth = displayList[tempIndex].strokeWidth
            stFill = displayList[tempIndex].fill
            stStroke =  displayList[tempIndex].stroke
            cpRec = Rectangle(displayList[tempIndex].xl, displayList[tempIndex].yl,  displayList[tempIndex].xr, displayList[tempIndex].yr)
            cpRec.strokeDash = displayList[tempIndex].strokeDash
            cpRec.strokeWidth = displayList[tempIndex].strokeWidth
            cpRec.fill = displayList[tempIndex].fill
            cpRec.stroke = displayList[tempIndex].stroke
        }
    }

    fun storeCp() {
        if (type2 == 2) {
            xl = displayList[tempIndex].xl
            yl = displayList[tempIndex].yl
            xr = displayList[tempIndex].xr
            yr = displayList[tempIndex].yr
            stDash = displayList[tempIndex].strokeDash
            stWidth = displayList[tempIndex].strokeWidth - 3.0
            stFill = displayList[tempIndex].fill
            stStroke =  displayList[tempIndex].stroke
            cpLine = Line(displayList[tempIndex].xl, displayList[tempIndex].yl, displayList[tempIndex].xr, displayList[tempIndex].yr)
            cpLine.strokeDash = displayList[tempIndex].strokeDash
            cpLine.strokeWidth = displayList[tempIndex].strokeWidth - 3.0
            cpLine.fill = displayList[tempIndex].fill
            cpLine.stroke = displayList[tempIndex].stroke
        }
        else if (type2 == 1) {
            xl = displayList[tempIndex].xl
            yl = displayList[tempIndex].yl
            r1 = displayList[tempIndex].r1
            stDash = displayList[tempIndex].strokeDash
            stWidth = displayList[tempIndex].strokeWidth - 3.0
            stFill = displayList[tempIndex].fill
            stStroke =  displayList[tempIndex].stroke
            cpCir = Circle(displayList[tempIndex].xl, displayList[tempIndex].yl, displayList[tempIndex].r1)
            cpCir.strokeDash = displayList[tempIndex].strokeDash
            cpCir.strokeWidth = displayList[tempIndex].strokeWidth - 3.0
            cpCir.fill = displayList[tempIndex].fill
            cpCir.stroke = displayList[tempIndex].stroke
        }
        else if (type2 == 3) {
            xl = displayList[tempIndex].xl
            yl = displayList[tempIndex].yl
            xr = displayList[tempIndex].xr
            yr = displayList[tempIndex].yr
            stDash = displayList[tempIndex].strokeDash
            stWidth = displayList[tempIndex].strokeWidth - 3.0
            stFill = displayList[tempIndex].fill
            stStroke =  displayList[tempIndex].stroke
            cpRec = Rectangle(displayList[tempIndex].xl, displayList[tempIndex].yl,  displayList[tempIndex].xr, displayList[tempIndex].yr)
            cpRec.strokeDash = displayList[tempIndex].strokeDash
            cpRec.strokeWidth = displayList[tempIndex].strokeWidth - 3.0
            cpRec.fill = displayList[tempIndex].fill
            cpRec.stroke = displayList[tempIndex].stroke
        }
    }

    fun updateCp() {
        if (type2 == 2) {
            cpLine = Line(xl, yl, xr, yr)
            cpLine.strokeDash = stDash
            cpLine.strokeWidth = stWidth
            cpLine.fill = stFill
            cpLine.stroke = stStroke
        }
        else if (type2 == 1) {
            cpCir = Circle(xl, yl, r1)
            cpCir.strokeDash = stDash
            cpCir.strokeWidth = stWidth
            cpCir.fill = stFill
            cpCir.stroke = stStroke
        }
        else if (type2 == 3) {
            print("got update\n")
            cpRec = Rectangle(xl, yl, xr, yr)
            cpRec.strokeDash = stDash
            cpRec.strokeWidth = stWidth
            cpRec.fill = stFill
            cpRec.stroke = stStroke
        }
    }


    fun addCp() {
        if (type2 == 2) {
            displayList.add(cpLine)
            listSize++
        }
        else if (type2 == 1) {
            displayList.add(cpCir)
            listSize++

        }
        else if (type2 == 3) {
            displayList.add(cpRec)
            listSize++
        }
    }

    fun copy() {
        if (theShape == 0) {
            type2 = displayList[tempIndex].type
            storeCp()
            ifcopyed = true
        }
    }

    fun paste() {
        if (ifcopyed) {
           // print("try to add: ${listSize} \n")
            addCp()
            updateCp()
        }
        simpleRedraw(gcP)
    }

    fun cut() {
        if (theShape == 0) {
            print("here1\n")
            type2 = displayList[tempIndex].type
            print("here2\n")
            storeCp2()
            print("here3\n")
            delete()
            print("here4\n")
            ifcopyed = true
            print("here5\n")
        }
        simpleRedraw(gcP)
    }

    fun removeView(view: IView?) {
        // remove view here
    }

    fun simpleRedraw(gc: GraphicsContext) {
        gc.clearRect(0.0, 0.0, gc.canvas.width, gc.canvas.height)
        for (b in displayList) {
            // print("got one\n")
            b.draw(gc)
        }
    }


    // the model uses this method to notify all of the Views that the data has changed
    // the expectation is that the Views will refresh themselves to display new data when appropriate
    private fun notifyObservers() {
        for (view in views) {
            println("Model: notify View")
            view.updateView()
        }
    }



    fun updategraph(x: Double, y: Double, gc: GraphicsContext) {
        print("${lineThick} |||| ${lineStyle} \n")
        if (theShape == 0) {

        }



        else if (theShape == 2) {
            tempShapeCir = Circle(pressX, pressY, distance(pressX, pressY, x, y))
            //tempShapeCir.setStyle()
            //setStyle("- setStrokeDashOffset(5)
            tempShapeCir.strokeDash = lineStyle
            tempShapeCir.strokeWidth = lineThick
            displayList.add(tempShapeCir)
            listSize++
        }
        else if (theShape == 3) {
            tempShapeLine = Line(pressX, pressY, x, y)
            tempShapeLine.strokeDash = lineStyle
            tempShapeLine.strokeWidth = lineThick
            displayList.add(tempShapeLine)
            listSize++
        }
        else if (theShape == 4) {
            tempShapeRec = Rectangle(pressX, pressY, x, y)
            tempShapeRec.strokeDash = lineStyle
            tempShapeRec.strokeWidth = lineThick
            displayList.add(tempShapeRec)
            listSize++
        }
        simpleRedraw(gc)

    }

    fun stDrag(x: Double, y: Double, gc: GraphicsContext) {
        if(ifPress && (theShape == 2 ||theShape == 3||theShape == 4 )) {
            //print("removedthing happen \n")
            displayList.removeAt(listSize - 1)
            listSize--
            updategraph(x, y, gc)
        }
        if (ifPress && theShape == 0) {
            print("changing\n")
            print("${x - lastx}\n")
            print("${y - lasty}\n")

            displayList[tempIndex].changexy(x - lastx, y - lasty)     // works like setTranslateProperty()
            lastx = x
            lasty = y
            updategraph(x, y, gc)
        }
    }

//    fun stMove(x: Double, y: Double, gc: GraphicsContext) {
//        if(ifPress) {
//            displayList.removeAt(listSize - 1)
//            listSize--
//            updategraph(x, y, gc)
//        }
//    }

    fun stRelease(x: Double, y: Double, gc: GraphicsContext) {
        if(ifPress) {
            ifPress = false
        }


    }

    fun clearselect2() {
        if (tempIndex >= 0) {

            displayList[tempIndex].strokeWidth = displayList[tempIndex].strokeWidth - 3
        }
        simpleRedraw(gcP)
    }


    fun clearselect() {
        if (tempIndex >= 0) {
            print("clearselect\n")
            displayList[tempIndex].strokeWidth = displayList[tempIndex].strokeWidth - 3
            tempIndex =  -1
        }
        simpleRedraw(gcP)
    }

    fun clearStyleB() {
        //print("collllllllllllllaaaaaa: ${line2.background.fills}\n")
       line2.setStyle("-fx-background-color: LightGrey; ")
       line3.setStyle("-fx-background-color: LightGrey; ")
       line1.setStyle("-fx-background-color: LightGrey; ")

    }
    fun clearStyleA() {
      //  print("collllllllllllll: ${line2.background.fills}\n")
        thinline.setStyle("-fx-background-color: LightGrey; ")
        midline.setStyle("-fx-background-color: LightGrey; ")
        thickline.setStyle("-fx-background-color: LightGrey; ")
    }


    fun delete() {
        if ( theShape == 0) {
            if(tempIndex >= 0) {
                displayList.removeAt(tempIndex)
                listSize--
                tempIndex = -1
                simpleRedraw(gcP)
            }
        }
    }

    fun stClick(x: Double, y: Double, gc: GraphicsContext) {
        clearselect()

//        val list = mutableListOf<Int>()
//        list.addAll(listOf(1, 2, 3))
//        list.add(4)
//        println(list) // [1, 2, 3]
//        list.remove(2)
////        for (s in list) {
////            println(list)
////             if (s == 2)  list.remove(s)
////        }
//        println(list)

        if (theShape == 1) {
            print("here delete\n")
            for (i in listSize - 1 downTo 0) {
                //displayList[i]
                if ( displayList[i].hittest(x, y)) {
                    print("found s ${x} ${y}\n")
                    displayList.removeAt(i)
                    listSize--
                    simpleRedraw(gc)
                    break
                }
            }
        }
        else if ( theShape == 0) {
            var foundt = false
           // print("here select\n")

            for (i in listSize - 1 downTo 0) {

                //displayList[i]
                if (  displayList[i].hittest(x, y)) {
                    clearStyleB()
                    clearStyleA()
                    tsfillCo.setValue(displayList[i].fill)
                    tslineCo.setValue(displayList[i].stroke)
                    fillCo = displayList[i].fill
                    print("recheck\n")
                   // print("coloorrrrrrrrrrrrrrrr: ${line2.background.fills}\n")
                    print("${displayList[i].strokeWidth}")
                    if (displayList[i].strokeDash == 24.0) line2.setStyle("-fx-background-color: DarkGrey; ")
                    else if  (displayList[i].strokeDash == 10.0)line3.setStyle("-fx-background-color: DarkGrey; ")
                    else if  (displayList[i].strokeDash == 0.0)line1.setStyle("-fx-background-color: DarkGrey; ")
                    if (displayList[i].strokeWidth == 1.0) thinline.setStyle("-fx-background-color: DarkGrey; ")
                    else if  (displayList[i].strokeWidth == 3.0)midline.setStyle("-fx-background-color: DarkGrey; ")
                    else if  (displayList[i].strokeWidth == 6.0)thickline.setStyle("-fx-background-color: DarkGrey; ")
                    lineStyle = displayList[i].strokeDash
                    lineThick = displayList[i].strokeWidth
                    displayList[i].strokeWidth = displayList[i].strokeWidth + 3
                    tempIndex = i
                    simpleRedraw(gc)
                    foundt = true
                    break
                }

            }
            if ( !foundt) {
                tempIndex = -1
            }
        }
        else if ( theShape == 5) {
            for (i in listSize - 1 downTo 0) {
                //displayList[i]
                if ( displayList[i].hittest(x, y)) {
                    print("found s ${x} ${y}\n")
                    displayList[i].fill = fillCo
                    simpleRedraw(gcP)
                    break
                }
            }
        }
    }

    fun stPress(x: Double, y: Double, gc: GraphicsContext) {
        pressX = x
        pressY = y

        if (theShape == 0) {
            clearselect()
            var foundt = false
            // print("here select\n")

            for (i in listSize - 1 downTo 0) {

                //displayList[i]
                if (  displayList[i].hittest(x, y)) {
                    tsfillCo.setValue(displayList[i].fill)
                    tslineCo.setValue(displayList[i].stroke)
                    lastx = pressX
                    lasty = pressY
                    displayList[i].strokeWidth = displayList[i].strokeWidth + 3
                    tempIndex = i
                    simpleRedraw(gc)
                    foundt = true
                    break
                }
            }
            if ( !foundt) {
                tempIndex = -1
            }
        }
        print("${listSize} size\n")
        if (theShape != 0) {
            updategraph(x, y, gc)
        }
        ifPress = true
    }


    fun setfillCo(bt: ColorPicker) {
        fillCo = bt.getValue()
        if (tempIndex >= 0) {
            print("setfillCo\n")

            displayList[tempIndex].fill = fillCo
            simpleRedraw(gcP)
        }
    }

    fun setlineCo(bt: ColorPicker) {
        lineCo = bt.getValue()
        if (tempIndex >= 0) {

            displayList[tempIndex].stroke = lineCo
            simpleRedraw(gcP)
        }

    }





    //endregion

    // simple accessor method to fetch the current state
    // of the data in the model, just a counter
    var counterValue = 0

    // method that the Controller uses to tell the Model to change state
    // in a larger application there would probably be multiple entry points like this
    fun incrementCounter() {
        counterValue++
        println("Model: increment counter to $counterValue")
        notifyObservers()
    }
}

