extends KinematicBody2D

# physics
const GRAVITY = 69

var acceleration = 50
var maxHorizontalSpeed = 600

var jumpHeight = 1250

var jumpQueued = false # if true the player will jump when contacting the ground
var grounded = false
var friction = false
var moveDirection = 0
var lastOnGround: int = 0 # in ms
var lastOffGround: int = 0 # in ms

var v = Vector2()

# mainly for player movement
func _physics_process(delta):
	friction = false
	v.y += GRAVITY
	
	moveDirection = int(Input.is_action_pressed("move_right")) - int(Input.is_action_pressed("move_left"))
	if moveDirection == 0:
		$Sprite.play("idle")
		friction = true
	else:
		$Sprite.play("run")
		if moveDirection > 0:
			$Sprite.flip_h = false
		else:
			$Sprite.flip_h = true
	
	v.x = clamp(v.x + moveDirection * acceleration, -maxHorizontalSpeed, maxHorizontalSpeed)
	
	if is_on_floor():
		lastOnGround = OS.get_ticks_msec()/10
		if jumpQueued && Input.is_action_pressed("jump"):
			v.y = -jumpHeight
			jumpQueued = false
		if friction:
			v.x = lerp(v.x, 0, 0.2)
	else:
		lastOffGround = OS.get_ticks_msec()/10
		if Input.is_action_just_pressed("jump") && lastOffGround - lastOnGround <= 7 && v.y > 0:
			v.y = -jumpHeight
			jumpQueued = false
		if v.y < 0:
			$Sprite.play("jump")
		else:
			$Sprite.play("fall")
		if friction:
			v.x = lerp(v.x, 0, 0.05)
	
	if Input.is_action_just_pressed("jump"):
		jumpQueued = true
	
	v = move_and_slide(v, Vector2.UP)
