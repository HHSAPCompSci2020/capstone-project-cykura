Cykura
Authors: Alex Zheng, Animan Patil, Vicram Vijayakumar
Revision: 4.26.21


Introduction: 
It is the year 2873. Your village has been invaded by an advanced alien race and all of the villagers were kidnapped by Doctor Bowgetaroth. You were also severely injured in the invasion. However you were taken in by a mysterious man and transformed into a cyborg. Meanwhile, the villain is planning to return to his home planet. Now, you, as the player, must defeat the villain and rescue the villagers while facing his minions.


In this 2D platformer game, the player must defeat various enemies while traversing the game to finally clear the villain. This game is intended for middle and high school students, but it can be played by anyone to get rid of boredom and have fun. By defeating each enemy, the player gains a new ability which can be used. These various abilities include: punch, fireball, waterfall, leaf dash, and double jump. However, defeating enemies is not all there is to the game. You must navigate through the level while avoiding spikes and holes in the ground to not get hurt with the help of platforms. Will you be able to vanquish Doctor Bowgetaroth and save your fellow villagers?




Instructions:
Starting Screen:
Click on Instructions to view instructions (Keyboard Keys Layout)
Click on Start game to start the game
Click on Quit to quit


Game Over Screen:
Press the Exit button to go back to the Starting Screen


Keyboard Keys Layout:
Left arrow key - Move to the left
Right arrow key - Move to the right
Up arrow key - Jump
Down arrow key - Drop down platforms/Dash down
Space Bar - Punch
A - Use Fireball
S - Use Waterfall
D - Use Leaf Dash




Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
* There should be 3 abilities that the enemies and players can have or collect. 
   * If a player/enemy gets hit with an ability, they lose health
   * Fireball shoots a fireball left and right of the player
      * 3 HP damage to enemies
      * ½ Heart damage to player
   * Waterfall shoots a waterfall up or down (based on whether the player is on the ground or not)
      * 7 HP damage to enemies
      * 1 Heart damage to player
   * Leaf dash allows the player to dash left and right and deal damage to enemies passed
      * Base Damage: 1 HP damage to enemies
      * Base Damage: ¼ Heart to player
      * Does not fall while dashing
      * Hold the ability button, D, to charge the dash
         * Longer the charge, longer the dash (distance covered), greater the damage
         * Max Damage: 10 HP damage to enemies
         * Max Damage: 1 Heart to player
         * Cannot charge for more than 5 seconds
         * When player is in air and charges, player will move horizontally to the right and will not fall after the button is released (depending on how long the charge was held)
         * Can fall while charging (while the button is held and the player is in the air)
* The enemies should be able to spawn and function correctly. The enemy should be able to use  abilities and they should be able to drop rewards/powers when defeated.
   * FireEnemy
      * Uses Fireball ability
      * Drops Fireball ability once defeated
      * HP: 100
   * WaterEnemy
      * Uses Waterfall ability
      * Drops Waterfall ability once defeated
      * HP: 100
   * GrassEnemy
      * Uses Leaf Dash ability
      * Drops Leaf Dash ability once defeated
      * HP: 100
   * Boss
      * Uses all abilities
      * HP: 175
* The hero should be able to move correctly and use abilities. After defeating minibosses the hero should be able to easily navigate between powers and use them properly. The player should also be able to regain health through the collection of hearts throughout the level by doing some parkour.
   * The hero should have five hearts to start
   * The amount of hearts the player currently has will be listed in the top left corner of the screen
   * If the hero loses all their health, they will go back to their checkpoint (last defeated enemy) with three hearts
* The singular level should have some artwork and the hero and enemies should be able to interact with the world properly.
   * The world should include platforms
   * The world should include holes in the ground
   * The world should include spikes which can induce damage to the player
   * The world should also include hearts which the player can obtain to regain health
   * The player’s hearts should be above the player
* There should be a final boss that is fully functional with specific abilities such as inverting movement controls, flipping the screen vertically, and flipping the screen horizontally. Once defeated the player will win the game


Want-to-have Features:
*  A timer recording the time taken for each playthrough
   * A timer will be at the right hand corner of the game
   * When the game is beat, the time will be displayed
   * Players can try to beat the game as fast as they can
   * Depending on the time, the player will receive a grade at the end of the game
* A hidden WindEnemy that will unlock the double jump skill
   * HP: 50
   * A barrier will be to the left of the player’s starting place
   * After the player punches the barrier, a health bar will show up for the barrier
   * Once the barrier is defeated, the miniboss will appear
   * Drops the Double Jump ability once the WindEnemy is defeated
* Background music
   * Depending on the enemy
*  Change character art depending on abilities
   * Gauntlets/gloves for fireball
   * Backpack for waterfall
   * Legs/Pants for leaf dash
   * Boots for double jump 
* Special attacks for the Boss
   * Ability to use attacks in diagonal directions
   * Ability to use two attacks at the same time (e.g. Waterfall + Fireball)


Stretch Features:
* A currency system. You can find coins/money on the ground and you can trade with villagers or other npcs for abilities and other benefits
   * Interact with ‘F’ key
*  Different end messages based on how the game is cleared
   * If game is completed while only defeating one miniboss, end will say “[Insert Element] Master”
      * Fire Master, Water Master, Leaf Master, Wind Master (Double jump)
   * If cleared all minibosses except for Wind (secret miniboss), end will say “Novice Elemental Master”
   * If cleared all minibosses including Wind, end will say “True Elemental Master”
   * If cleared without defeating a single miniboss, end will say “Fist King”
*  Sound effects for each ability, the monsters, and movement




Class List:
* Hero: The player’s character which can move and use special abilities
* Enemy: A superclass for all of the different types of enemies against the Hero
* FireEnemy: A subclass which uses the fireball projectile
* WaterEnemy: A subclass which uses the waterfall projectile
* GrassEnemy: A subclass which uses the leaf dash move
* <If Implemented> WindEnemy: A subclass which uses the double jump ability
* Boss: A subclass which is able to use all of the abilities
* Projectile: Projectiles which players and enemies can use to damage other
* Fireball: A subclass of the Ability class which shoots a fireball
* Waterfall: A subclass of the Ability class that shoots a waterfall up or down
* DrawingSurface: The surface which draws all of the different Objects
* Main: The class which has the DrawingSurface and Window
* Platform: Class representing the moving platforms player and hero can walk on with potential spikes
* MovingImage: A superclass that represents an Image which can move 




Credits:
* Work Split:
Animan: FireEnemy, DrawingSurface, Enemy
Vicram: WaterEnemy, Hero, Waterfall, Platform
Alex: GrassEnemy, Main, Ability, Fireball
All: Boss, WindEnemy (if coded)


* Outside Credit:
DrawingSurface Base Code <Modified by Animan>: Mr. Shelby
MovingImage: Mr. Shelby
Hero Class Base Code <Modified by Vicram>: Mr. Shelby


* Outside Resources:
Hero Character Sprite Credit: 
https://drive.google.com/file/d/1dmXliJtleGvaMhvep4Mn6j-y9HCR5R_Z/view?usp=sharing
Hero Sprite: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=maskD&nose=button&shoes=boots_black1&legs=pants_teal&clothes=formal_striped&mail=none&armor=12&jacket=none&arms=1&shoulders=none&bracers=none&greaves=2&gloves=3&belt=none&cape=none&hair=none&ears=elven&hat=morion
Enemy Sprite Credit:
https://drive.google.com/file/d/1JGEBLbEOWJ64ST1E9fNlpPLTvu5f45-0/view?usp=sharing
Enemy Sprite: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=none&nose=button&shoes=boots_1&legs=pants_teal&clothes=formal_striped&mail=none&armor=15&jacket=none&arms=2&shoulders=none&bracers=none&greaves=5&gloves=1&belt=none&cape=none&hair=none&ears=big&hat=plate5&shield=none&capeacc=none&eyes=none
Fire Enemy Sprite Credit:
https://drive.google.com/file/d/1-rQfYtPvoS2B0jIpQcygxtFOvB9hT-2m/view?usp=sharing
Fire Enemy Sprite: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=none&nose=button&shoes=boots_6&legs=pants_teal&clothes=formal_striped&mail=none&armor=16&jacket=none&arms=6&shoulders=none&bracers=none&greaves=6&gloves=7&belt=none&cape=male_red&hair=none&ears=big&hat=horned6&shield=none&capeacc=none&eyes=none
Water Enemy Sprite Credit:
https://drive.google.com/file/d/1H1-Sx-D7XyojQTA1tw2yuulRNNjoAi_a/view?usp=sharing
Water Enemy Sprite: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=none&nose=button&shoes=boots_2&legs=none&clothes=none&mail=none&armor=chest_leather3&jacket=none&arms=2&shoulders=none&bracers=none&greaves=2&gloves=3&belt=none&cape=male_blue&hair=none&ears=big&hat=horned2&shield=none&capeacc=none&eyes=none
Grass Enemy Sprite Credit:
https://drive.google.com/file/d/16YZJGXwb2KcsXQTLQy4JHaNj6WTZUzwS/view?usp=sharing
Grass Enemy Sprite: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=none&nose=button&shoes=boots_2&legs=pants_formal_stripes&clothes=none&mail=none&armor=chest_leather2&jacket=none&arms=2&shoulders=none&bracers=none&greaves=none&gloves=3&belt=none&cape=none&hair=none&ears=big&hat=horned2&shield=none&capeacc=none&eyes=none
Wind Enemy Sprite Credit:
https://drive.google.com/file/d/1iRpbgks-wXQ9-k_II-4A9as-Sk7rHh0w/view?usp=sharing
Wind Enemy: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=none&nose=button&shoes=sandalsroman&legs=none&clothes=none&mail=none&armor=11&jacket=none&arms=2&shoulders=legion_steel&bracers=none&greaves=1&gloves=4&belt=none&cape=male_gray&hair=none&ears=big&hat=plate2&shield=none&capeacc=none&eyes=none
Boss Enemy Sprite Credit:
https://drive.google.com/file/d/1A8TZtyKXljocyp3IqByI84jk9w875BNX/view?usp=sharing
Boss Enemy Sprite: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?shadow-adult=0&facial=none&nose=button&shoes=boots_3&legs=none&clothes=none&mail=none&armor=14&jacket=none&arms=5&shoulders=none&bracers=none&greaves=5&gloves=4&belt=none&cape=none&hair=none&ears=big&hat=horned4&shield=none&capeacc=none&eyes=none
Platform Sprite Credit:
https://opengameart.org/content/platformer-art-pixel-edition
Kenny.nl
Heart Sprite Credit:
heart pixel art | OpenGameArt.org
Credit DanSevenStar.xyz, DontMind8.blogspot.com