import javafx.scene.canvas.GraphicsContext
import javafx.geometry.Point2D
import java.util.DoubleSummaryStatistics

class Rectangle(
    var x: Double,
    var y: Double,
    var x2: Double,
    var y2: Double
): Shape() {
    open var xori = x
    open var yori = y

    override fun changexy(xc:Double, yc: Double) {
        x = x + xc
        y = y + yc
        xori = x
        yori = y
        x2 = x2 + xc
        y2 = y2 + yc
    }





    override fun draw(gc: GraphicsContext) {
        type = 3
        xl = x
        yl = y
        xr = x2
        yr = y2
        val width = x2 - x
        val height = y2 - y
        if (isFilled) {
            gc.fill = fill
            gc.fillRect(x, y, width, height)
        }
        if (isStroked) {
            gc.stroke = stroke
            gc.lineWidth = strokeWidth
            gc.setLineDashes(strokeDash)
            gc.strokeRect(x, y, width, height)
        }
    }

    override fun hittest(mx: Double, my: Double): Boolean {


        // use JavaFX Point2D to calculate distance
        // inside hit-test
        if (isFilled) {
            if (x + strokeWidth/2 < mx && mx < x2 - strokeWidth/2 && y + strokeWidth/2 < my && my < y2 -strokeWidth/2) return true
        }

        // edge hit-test
        if (isStroked) {
            print("try rec stroke")
           if (x - strokeWidth/2 <= mx && mx <= x2 + strokeWidth/2) {
               if (y - strokeWidth/2 <= my && my <= y2 + strokeWidth/2) return true
           }
//            else if (x2 - strokeWidth/2 < mx && mx < x2 + strokeWidth/2) {
//                if (y1 - strokeWidth/2 < my && my < y2 + strokeWidth/2) return true
//            }
//            else if (y1 - strokeWidth/2 < my && my < y1 + strokeWidth/2 ) {
//                if (x1 - strokeWidth/2 < mx && mx < x2 + strokeWidth/2) return true
//            }
//           else if (y2 - strokeWidth/2 < my && my < y2 + strokeWidth/2 ) {
//               if (x1 - strokeWidth/2 < mx && mx < x2 + strokeWidth/2) return true
//           }


        }
        return false



    }
}