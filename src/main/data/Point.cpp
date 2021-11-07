#include "Point.h"

#include "utilities.h"

//------------------------------------------------------------------------------
Point::Point()
    :x(0), y(0), z(0)
{
}

//------------------------------------------------------------------------------
Point::Point(double x_, double y_, double z_)
    :x(x_), y(y_), z(z_)
{
}

//------------------------------------------------------------------------------
void Point::scale(double scalingFactor)
{
    x *= scalingFactor;
    y *= scalingFactor;
    z *= scalingFactor;
}

//------------------------------------------------------------------------------
void Point::display(std::ostream& outs) const
{
    outs << "("  << x
         << ", " << y
         << ", " << z
         << ")";
}

//------------------------------------------------------------------------------
bool operator==(const Point& lhs, const Point& rhs)
{
    if (!fpNumsAreEqual(lhs.x, rhs.x)) {
        return false;
    }

    if (!fpNumsAreEqual(lhs.y, rhs.y)) {
        return false;
    }

    if (!fpNumsAreEqual(lhs.z, rhs.z)) {
        return false;
    }

    return true;
}
