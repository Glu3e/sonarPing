# Authors: Daniel Kerkreswell, Joshua Collaco

import bpy
import json

from pprint import pprint

with open('C:\\Users\\Jupiter\\Desktop\\Javascript\\Work\\sonarPing\\pythonScripts\\strings.json') as data_file:    
    data = json.load(data_file)

pprint(len(data))

for i in range(len(data)):
	pprint(i)

	mesh = bpy.data.meshes.new("Cube")
	verts = [(data[i]['pointx'], data[i]['pointy'],data[i]['pointz'])]

	object = bpy.data.objects.new("Cube", mesh)

	bpy.context.scene.objects.link(object)

	mesh.from_pydata(verts,[],[])
	mesh.update(calc_edges=True)


	
	

	


