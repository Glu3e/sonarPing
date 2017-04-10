import bpy
import json

from pprint import pprint

with open('C:\\Users\\Jupiter\\Desktop\\Javascript\\Work\\sonarPing\\pythonScripts\\strings.json') as data_file:    
    data = json.load(data_file)
pprint(data)


#Define vertices and faces (replace these test values with input arrays later, this code will create a cube)
verts = [(data[0]['pointx'], data[0]['pointy'],data[0]['pointz'])]
faces = [(0,1,2,3), (4,5,6,7), (0,4,5,1), (1,5,6,2), (2,6,7,3), (3,7,4,0)]



#Define mesh and object
mesh = bpy.data.meshes.new("Cube")
object = bpy.data.objects.new("Cube", mesh)
 
#Set location and scene of object
object.location = bpy.context.scene.cursor_location
bpy.context.scene.objects.link(object)
 
#Create mesh
mesh.from_pydata(verts,[],faces)
mesh.update(calc_edges=True)