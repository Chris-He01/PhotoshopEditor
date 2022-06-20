import javafx.geometry.Point2D
import javafx.scene.paint.Color
import javafx.scene.canvas.*
import java.util.DoubleSummaryStatistics

abstract class Shape(
    var fill: Color? = Color.LIGHTGREY,
    var stroke: Color? = Color.BLACK,
    var strokeWidth: Double = 1.0,
    var strokeDash: Double = 0.0,
    var type: Int = 0,
    var xl: Double = 0.0,
    var yl: Double = 0.0,
    var xr: Double = 0.0,
    var yr: Double = 0.0,
    var r1: Double = 0.0
){


    open val isFilled: Boolean
        get() = (fill != null)

    open val isStroked: Boolean
        get() = (stroke != null)

//    abstract fun returnx(): Double

    abstract fun changexy(xc:Double, yc: Double)

    abstract fun draw(gc: GraphicsContext)

    abstract fun hittest(mx: Double, my: Double) : Boolean
}






