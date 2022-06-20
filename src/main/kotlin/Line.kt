import javafx.scene.canvas.GraphicsContext
import javafx.geometry.Point2D

class Line (
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
        type = 2

        xl = x
        yl = y
        xr = x2
        yr = y2
        if (isStroked) {
            gc.stroke = stroke
            gc.lineWidth = strokeWidth
            gc.setLineDashes(strokeDash)
            gc.strokeLine(x, y, x2, y2)
            print("${strokeWidth} stroke width\n")

        }
    }

    override fun hittest(mx: Double, my: Double): Boolean {

        // edge hit-test
        if (isStroked) {
            val m = Point2D(mx, my)
            val q = closestPoint(m, Point2D(x, y), Point2D(x2, y2))
            if (strokeWidth < 10) {
                if (m.distance(q) <= 5) return true
            }
            else {
                if (m.distance(q) <= strokeWidth / 2) return true
            }

        }

        // no hit
        return false
    }
}