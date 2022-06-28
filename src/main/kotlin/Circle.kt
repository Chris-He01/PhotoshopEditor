import javafx.geometry.Point2D
import javafx.scene.canvas.GraphicsContext
import java.util.DoubleSummaryStatistics

class Circle(
    var x: Double,
    var y: Double,
    var r: Double
): Shape() {
    open var xori = x
    open var yori = y


    override fun changexy(xc:Double, yc: Double) {
        x = x + xc
        y = y + yc
        xori = x
        yori = y
    }

    override fun draw(gc: GraphicsContext) {
        type = 1
        r1 = r
        xl = x
        yl = y
        if (isFilled) {
            gc.fill = fill
            gc.fillOval(x - r, y - r, r * 2, r * 2)
        }
        if (isStroked) {
            gc.stroke = stroke
            gc.lineWidth = strokeWidth
            gc.setLineDashes(strokeDash)
            gc.strokeOval(x - r, y - r, r * 2, r * 2)
        }
    }

    override fun hittest(mx: Double, my: Double): Boolean {

        // use JavaFX Point2D to calculate distance
        val p = Point2D(x, y)
        val m = Point2D(mx, my)
        val dist = p.distance(m)

        // inside hit-test
        if (isFilled) {
            if (dist <= r) return true
        }

        // edge hit-test
        if (isStroked) {
            if (dist >= r - strokeWidth/2 &&
                dist <= r + strokeWidth/2 ) return true
        }

        // no hit
        return false
    }
}