extends KinematicBody2D



onready var tilemap = get_tree().current_scene.get_node("TileMap")
export var flip = false
export var hp = 4

export var hv = 500
var v = Vector2(hv, 0)

func changeDirection():
	flip = !flip
	$Sprite.flip_h = flip
	$ObjectDetector.rotation_degrees = 180 if flip else 0
	v.x *= -1

func checkWall(tile):
	var tileInfo = tilemap.get_cellv(tile)
	if tileInfo == -1:
		changeDirection()

func _physics_process(delta):
	v.y += 10
	
	# check for ledge
	if !flip:
		checkWall(tilemap.world_to_map(global_position + Vector2(64, 64)))
	else:
		checkWall(tilemap.world_to_map(global_position + Vector2(-64, 64)))
	
	$Sprite.play()
	move_and_slide(v, Vector2.UP)


func _on_ObjectDetector_body_entered(body):
	changeDirection()
