#ifndef POINT_H_INCLUDED
#define POINT_H_INCLUDED

#include <iostream>

/**
 * Coordinate in 3 dimensional Cartesian space
 */
struct Point {
    double x, y, z;

    /**
     * Default Constructor
     */
    Point();

    /**
     * Construct a Point from specified
     * x, y, and z values
     */
    Point(double x, double y, double z);

    // Use the compiler generated version
    Point(const Point& src) = default;

    // Use the compiler generated version
    ~Point() = default;

    // Use the compiler generated version
    Point& operator=(const Point& rhs) = default;

    /**
     * Apply geometric scaling function
     */
    void scale(double scalingFactor);

    /**
     * Print a point
     */
    void display(std::ostream& outs) const;

    /**
     * Swap the contents of two `Point`s
     * <p>
     * I am using a friend function here and only here (under protest)
     */
    friend
    void swap(Point& lhs, Point& rhs);
};

/**
 * Logical Equivalence Operator - Weak Equality
 */
bool operator==(const Point& lhs, const Point& rhs);

/**
 * Stream insertion (output) operator
 */
inline
std::ostream& operator<<(std::ostream& outs, const Point prt)
{
    prt.display(outs);
    return outs;
}

//------------------------------------------------------------------------------
inline
void swap(Point& lhs, Point& rhs)
{
    std::swap(lhs.x, rhs.x);
    std::swap(lhs.y, rhs.y);
    std::swap(lhs.z, rhs.z);
}

#endif
