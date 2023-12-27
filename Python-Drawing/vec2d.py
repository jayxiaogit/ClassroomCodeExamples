import math

class Point:
    """
    the __init__ function sets the point's x and y
    """
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y
    
    """
    below functions are overloading operators, which determine what happens to the point when it is operated upon
    """
    def __str__(self):
        """
        determined what is printed when you print a point
        
        """
        return "x: {} y: {}".format(self.x, self.y)
    
    def __add__(self, other):
        """
        addition overloader, determines what happens when points are added
        
        """
        return Point(self.x + other.x, self.y + other.y)
        
    def __iadd__(self, other):
        """
        another addition overloader, determines what happens when += is used
        
        """
        self.x = self.x + other.x
        self.y = self.y + other.y
        return Point(self.x, self.y)
    
    def __sub__(self, other):
        """
        subtractoin overloader, determines what happens when points are subtracted
        
        """
        return Point(self.x - other.x, self.y - other.y)
        
    def __isub__(self, other):
        """
        subtraction overloader, determines what happens when -= is used
        
        """
        return self - other
    
    def getX(self):
        """
        function used to return self.x
        
        """
        return self.x
    
    def getY(self):
        """
        function used to return self.y
        
        """
        return self.y
        

    pass
    
class Vec2D(Point):
   #I will need to make if else cases for different argument types
    """
    the __init__ can accept multiple different types of arguments and then sets an x and y for the point
    """
    def __init__(self, a = None, b = None):
        if a is None and b is None:
            self.x = 0
            self.y = 0
        elif type(a) == Point and b is None:
            self.x = a.x
            self.y = a.y
        elif type(a) == Point and type(b) == Point:
            self.x = b.x - a.x
            self.y = b.y - a.y
        elif (type(a) == float or type(a) == int) and (type(b) == float or type(b) == int):
            self.x = a
            self.y = b
            
    """
    overloading operator functions below, these establish what the point does when it is operated upon
    """    
    def __add__(self,other):
        """
        addition overloader, determines what happens when points are added
        
        """
        return Vec2D(self.x + other.x, self.y + other.y)
        
    def __sub__(self, other):
        """
        subtractoin overloader, determines what happens when points are subtracted
        
        """
        return Vec2D(self.x - other.x, self.y - other.y)
    
    def __isub__(self, other):
        """
        subtraction overloader, determines what happens when -= is used
        
        """
        return (self - other)
    
    def __iadd__(self, other):
        """
        another addition overloader, determines what happens when += is used
        
        """
        return (self + other)
    
    #need to make multiple cases of __mul__ for diff types of arguments
        
    def __mul__(self, other):
        """
        multiplication overloader, determines what happens when vectors are multiplied, depending on the arguments
        
        """
        if type(other) == int or type(other) == float:
            #print("urdad")
            #print(other)
            new_x = self.x * other
            #print(self.x)
            new_y = self.y * other
            #print(self.y)
            return Vec2D(new_x,new_y)
        elif type(other) == Point or type(other) == Vec2D:
            #print('urmom')
            return  self.x * other.x + self.y * other.y
            
        #elif type(arg) == 'vec2d.Vec2D':
        #    self.x = self.x * arg.x
        #    self.y = self.y * arg.y
    
    """
    this function returns the magnitude of the vector entered
    """
    def norm(self):
        """
        returns the magnitude of a vector
        
        """
        self.mag = math.sqrt((self.x**2) + (self.y**2))
        return float(self.mag)
    
    
    pass


if __name__=='__main__':
    a = Point(2,1)
    print(a)
    b = Point(4,2)
    print(a + b)
    print(type(a + b))
    print(a -b)
    print(type(a - b))
    c = Point(2, 2)
    a += c
    print(a)
    print(type(c))
    b -= c
    print(b)
    print(type(b))
    e = Vec2D()
    f = Vec2D(3,4)
    g = Vec2D(Point(4,5), Point(2,1))
    h = Vec2D(Point(5,6))
    print(e)
    print(f)
    print(g)
    print(h)
    scaled = Vec2D(1,1)*2
    print(scaled, type(scaled))
    dot_prod = Vec2D(4,5) * Vec2D(1,0)
    print( dot_prod, type(dot_prod))
    dot_prod = Vec2D(4,5) * Point(1,1)
    print( dot_prod, type(dot_prod))
    print('slay', Vec2D(4,5) + Vec2D(1,0))
    pass

