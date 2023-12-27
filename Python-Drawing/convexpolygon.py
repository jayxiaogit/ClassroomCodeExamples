from vec2d import Point
from vec2d import Vec2D

import math

class ConvexPolygon:

    ''' 
    Convex Polygons are shapes with angles all less than 180 degrees
    edges : contains the list of Vec2D objects with the edges of the polygons
    nverts : an int that holds the number of vertices of the polygon object
    verts : a list of Point objects that holds the vertices of the polygon
    '''
    

    def __init__(self, verts):
        """
        Parameters
        ----------
        verts : list
        This function creates a ConvexPolygon after accepting a list of vertices
        Returns
        -------
        None.

        """
        self.nverts = int(len(verts))
        self.verts = verts
        edges = []
        if self.nverts < 3:
            raise ValueError
        for i in range(self.nverts - 1):
            edges.append(Vec2D(self.verts[i], self.verts[i + 1])) 
        edges.append(Vec2D( self.verts[-1], self.verts[0])) 
        self.edges = edges
        
    
    def __str__(self):
        """
        this function is the default for what will print if you print a ConvexPolygon
        Returns
        -------
        str
            DESCRIPTION.

        """
        nv = 'No. of Vertices: '+str(self.nverts)+'\n'
        vs = "Vertices "+" ".join([v.__str__() + ', ' for v in self.verts]) + '\n'
        es = "Edges "+ " ".join([e.__str__() + ', ' for e in self.edges]) 
        return nv + vs + es
    
    def translate(self, vec):
        """

        Parameters
        ----------
        vec : Vec2D
        This function moves a polygon in the direction of a specified vector, and updates the vertices of the polygon

        Returns
        -------
        None.

        """
        for i in range((len(self.verts))):
            self.verts[i].x += vec.x
            self.verts[i].y += vec.y
        return None
        
    #setup for the rotate function
    def get_centroid(self):
        """
        this function sets up the default point for the rotate function if a point is not inputted
        Returns
        -------
        pointdef : centroid

        """
        e = 0
        for i in range(self.nverts - 1):
            #print(self.edges[i].x)
            #print(type(self.edges[i].x))
            e += (self.verts[i].x * self.verts[i + 1].y) - (self.verts[i + 1].x * self.verts[i].y)
        e += (self.verts[-1].x * self.verts[0].y) - (self.verts[0].x * self.verts[-1].y)
        #print('e', e)
        A = (1/2) * e
        #print(A)
        eq1 = 0
        for i in range(self.nverts - 1):
            eq1 += (self.verts[i].x + self.verts[i + 1].x) * ((self.verts[i].x * self.verts[i + 1].y) - (self.verts[i + 1].x * self.verts[i].y))
        eq1 += (self.verts[-1].x + self.verts[0].x) * ((self.verts[-1].x * self.verts[0].y) - (self.verts[0].x * self.verts[-1].y))
        centx = (1/(6 * A)) * eq1
        eq2 = 0
        for i in range(self.nverts - 1):
            eq2 += (self.verts[i].y + self.verts[i + 1].y) * ((self.verts[i].x * self.verts[i + 1].y) - (self.verts[i + 1].x * self.verts[i].y))
        eq2 += (self.verts[-1].y + self.verts[0].y) * ((self.verts[-1].x * self.verts[0].y) - (self.verts[0].x * self.verts[-1].y))
        centy = (1/(6 * A)) * eq2
        pointdef = Point(centx, centy)
        #print('point',pointdef)
        return pointdef
    
    def rotate(self, theta, pivot = None):
        """
        Parameters
        ----------
        theta : int or float, the angle of pivot
            DESCRIPTION.
        pivot : Point, optional
        This function will rotate a polygon in the counter-clockwise direction about a pivot point. If no point is entered, the default is the center. Rotate updates the edges and vertices of the polygon after rotation

        Returns
        -------
        None.

        """
        if pivot == None:
                pivot = self.get_centroid()
        for i in range(self.nverts):
            changex = (math.cos(theta) * (self.verts[i].x - pivot.x)) - (math.sin(theta) * (self.verts[i].y - pivot.y)) + pivot.x
            changey = (math.sin(theta) * (self.verts[i].x - pivot.x)) + (math.cos(theta) * (self.verts[i].y - pivot.y)) + pivot.y
            self.verts[i].x = changex
            self.verts[i].y = changey
        for i in range(self.nverts):
            if i < self.nverts - 1:
                self.edges[i] = Vec2D(self.verts[i],self.verts[i + 1])
            else: 
                self.edges[i] = Vec2D(self.verts[-1],self.verts[0])
        return None
            
    def scale(self, s1, s2):
        """
        Parameters
        ----------
        s1 : postive float
        s2 : positive float
        scale accepts two positive float objects and scales the polygon by those factors. It will update the edges and vertices of the polygon after scaling

        Returns
        -------
        None

        """
        if type(s1) == int or type(s2) == int:
            s1 = float(s1)
            s2 = float(s2)
        if type(s1) != float or type(s2) != float or s2 < 0 or s1 < 0 :
            print('scale factors must be positive floats')
            return False
        else:
            pivot = self.get_centroid()
            for i in range(self.nverts):
                changex = s1 * (self.verts[i].x - pivot.x) + pivot.x
                changey = s2 * (self.verts[i].y - pivot.y) + pivot.y
                self.verts[i].x = changex
                self.verts[i].y = changey
            for i in range(self.nverts):
                if i < self.nverts - 1:
                    self.edges[i] = Vec2D(self.verts[i],self.verts[i + 1])
                else: 
                    self.edges[i] = Vec2D(self.verts[-1],self.verts[0])
        return None
                   
    def __and__(self, other):
        """
        Parameters
        ----------
        other : ConvexPolygon
        this overloaded operator will return True if two polygons overlap and False otherwise.

        Returns
        -------
        None.

        """
        #print('selfv', self.verts)
        #print('self', self.edges)
        #print('other', other.edges)    
        i = 0
        result = True
        E = self.edges
        for i in range(len(other.edges)):
            E.append(other.edges[i])
        oi = Vec2D(-E[i].y, E[i].x)
        projection = []
        for i in range(len(E)):
            for i in range(self.nverts):
                projection.append(oi * self.verts[i])
            minA = min(projection)
            maxA = max(projection)
            projection2 = []
            for i in range(int(len(other.verts))):
                projection2.append(oi * other.verts[i])
            minB = min(projection2)
            maxB = max(projection2)
            if maxA < minB or maxB < minA:
                result = False
                return result
            elif result != False:
                i += 1
                if i == (len(E)):
                    result = True
        return result
    
    
   
if __name__=='__main__':
    a = ConvexPolygon([Point(1,0), Point(0,1), Point(-1,0), Point(0,-1)])
    print('before rotation\n')
    print(a)
    print("\nAfter rotation\n")
    a.rotate(math.pi/4)
    print(a)
    a = ConvexPolygon([Point(1,0), Point(0,1), Point(-1,0), Point(0,-1)])
    print('b4',a)
    a.scale(2, 2)
    print('after',a)
    a = ConvexPolygon([Point(1,0), Point(0,1), Point(-1,0), Point(0,-1)])
    a.translate(Vec2D(3,0))
    print(a)
    a = ConvexPolygon([Point(1,0), Point(0,1), Point(-1,0), Point(0,-1)])
    b = ConvexPolygon([Point(3,0), Point(-1,2), Point(-1,0)])
    c = ConvexPolygon([Point(6,0), Point(2,2), Point(2,0)])
    print (a & b)
    print(a & c)
    print(b & c)
    pass
