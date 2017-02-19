import bpy
 
#Define vertices and faces (replace these test values with input arrays later, this code will create a cube)
verts = [(0,0,0),(0,5,0),(5,5,0),(5,0,0),(0,0,5),(0,5,5),(5,5,5),(5,0,5)]
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