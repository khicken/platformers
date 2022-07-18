extends Sprite

var autoReload = true

var shakeMagnitude = 0.5
var recoilDistance = 10
var fireRate = 0.2
var bulletSpeed = 1000
var magazineCapacity = 20
var reloadTime = 2.0
var bulletsLeftInGun = 50 # bullets that are NOT in magazine
var bullet = preload("res://scenes/Bullet.tscn")

onready var screenShaker = get_node("/root/Main/Player/Camera/Screenshaker")
var bulletsInMagazine = 0
var canFire = false
var outOfAmmo = false
var reloading = false

func _ready():
	set_as_toplevel(true)

func reload():
	if outOfAmmo:
		return	
	canFire = false
	var bulletsTakingFromGun = min(bulletsLeftInGun, magazineCapacity - bulletsInMagazine)
	bulletsLeftInGun -= bulletsTakingFromGun
	bulletsInMagazine += bulletsTakingFromGun
	yield(get_tree().create_timer(reloadTime), "timeout")
	canFire = true
	reloading = false

func _physics_process(delta):
	# update weapon position
	position.x = lerp(position.x, get_parent().position.x, 0.75)
	position.y = lerp(position.y, get_parent().position.y, 0.75)
	
	# set rotation
	var xDistance = get_global_mouse_position().x - position.x
	var yDistance = get_global_mouse_position().y - position.y
	var weaponRotation = atan2(yDistance, xDistance)
	if xDistance < 0:
		flip_h = true
		set_rotation(weaponRotation+PI)
	else:
		flip_h = false
		set_rotation(weaponRotation)
	
	# what to do when no bullets left in magazine
	if bulletsInMagazine == 0:
		canFire = false
		if bulletsLeftInGun == 0:
			outOfAmmo = true
		if autoReload && !outOfAmmo && !reloading:
			reloading = true
			reload()
	
	# reload
	if Input.is_action_pressed("reload") && !reloading:
		if !outOfAmmo:
			reloading = true
			reload()
	
	# firing
	if Input.is_action_pressed("fire") && canFire && !reloading:
		bulletsInMagazine -= 1
		$WeaponAudioPlayer.play()
		var bulletInstance = bullet.instance()
		bulletInstance.rotation = weaponRotation
		# fire bullet from the tip of the gun
		bulletInstance.global_position = $Position2D.global_position + Vector2(texture.get_width()*scale.x/2*cos(weaponRotation), (scale.y*400+texture.get_height()*scale.y/2)*sin(weaponRotation))
		position.x -= recoilDistance * cos(weaponRotation)
		position.y -= recoilDistance * sin(weaponRotation)
		screenShaker.shake(fireRate, shakeMagnitude)
		get_parent().add_child(bulletInstance)
		canFire = false
		yield(get_tree().create_timer(fireRate), "timeout")
		canFire = true
