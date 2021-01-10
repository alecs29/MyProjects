#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

#define SCREEN_WIDTH 128 // OLED display width, in pixels
#define SCREEN_HEIGHT 64 // OLED display height, in pixels

#define UP_BUTTON_P1 7
#define DOWN_BUTTON_P1 6
#define UP_BUTTON_P2 5
#define DOWN_BUTTON_P2 4
#define AI_BUTTON 3
#define AI_LED 2
#define PAUSE_BUTTON 1

// SPI 
#define OLED_MOSI   9 //d1
#define OLED_CLK   10 //d0
#define OLED_DC    12
#define OLED_CS    11
#define OLED_RESET 13
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT,
  OLED_MOSI, OLED_CLK, OLED_DC, OLED_RESET, OLED_CS);

#define RED 0xF800


//------------------------------------------ GAME CONSTANTS
const int MAX_SCORE = 5;
const int PADDLE_SIZE = 18;

//------------------------------------------- GAME VARIABLES
//----------------------------------players
int player1Score = 0, player2Score = 0; 

//----------------------------------ball
unsigned int ballX=64, ballY=32; 
unsigned int ballDirX=1, ballDirY=1; 


//----------------------------------paddles
unsigned int paddle2X = 8;
unsigned int paddle2Y = 16;

unsigned int paddle1X = 120;
unsigned int paddle1Y = 16;

//----------------------------------other
boolean GameRunning = true;
boolean ResetBall = false;


//-------------------------------------------------------- UTILS - MESSAGE  FUNCTIONS
void welcome()                                                        //####
{
  display.setCursor(3,1);
  display.setTextColor(WHITE);
  display.setTextSize(2);
  display.setTextWrap(false);
  display.print("WELCOME TO");

  display.setCursor(21,22);
  display.setTextColor(WHITE);
  display.setTextSize(2);
  display.setTextWrap(false);
  display.print("ARDUINO");
  
  display.setCursor(6,45);
  display.setTextColor(WHITE);
  display.setTextSize(2);
  display.setTextWrap(false);
  display.print("PONG GAME!");

  display.display();
  display.clearDisplay();
  delay(2000);
}
void pressButtonToStart()                                                        //####
{
  display.setCursor(3,20);
  display.setTextColor(WHITE);
  display.setTextSize(1);
  display.setTextWrap(false);
  display.print("press button to enter");
  display.display();                                                        
  display.clearDisplay();
  delay(200);
  
}
void printNumberUtil(int nr, int x, int y, int textSize)                                                      //####
{
  display.setCursor(x,y);
  display.setTextColor(WHITE);
  display.setTextSize(textSize);
  display.setTextWrap(false);
  display.print(nr);                                                       
}

void printStringUtil(String str, int x, int y, int textSize, uint16_t color)                                                      //####
{
  display.setCursor(x,y);
  display.setTextColor(color);
  display.setTextSize(textSize);
  display.setTextWrap(false);
  display.print(str);                                                       
}
void printIntermScore()                                                      //####
{
  display.setCursor(5+ 47, 2);
  display.setTextColor(WHITE);
  display.setTextSize(1);
  display.setTextWrap(false);
  display.print(player2Score); 
  display.setCursor(12+ 47, 2);
  display.print("-");  
  display.setCursor(20 + 47, 2);  
  display.print(player1Score);                                                     
}


//--------------------------------------------------    SETUP    -------------------------------------------------- //
void setup()
{
  pinMode(UP_BUTTON_P1, INPUT_PULLUP);
  pinMode(DOWN_BUTTON_P1, INPUT_PULLUP); 
  pinMode(UP_BUTTON_P2, INPUT_PULLUP);
  pinMode(DOWN_BUTTON_P2, INPUT_PULLUP); 
  pinMode(AI_BUTTON, INPUT_PULLUP);
  pinMode(AI_LED, INPUT_PULLUP);
  pinMode(PAUSE_BUTTON, INPUT_PULLUP);
  digitalWrite(AI_LED, LOW);
  
  Serial.begin(9600);
  if(!display.begin(SSD1306_SWITCHCAPVCC)) {
    Serial.println(F("SSD1306 allocation failed"));
    for(;;); // Don't proceed, loop forever
  }
  
  display.display();
  display.clearDisplay();
  delay(1000); // Pause for 1 sec
  welcome();
  pressButtonToStart();
  while(buttonsUnpressed())  
  {

    delay(100);
  }
    

  delay(500);
  
  display.clearDisplay();
  drawScreenMargins();
  display.display();

  
}
//--------------------------------------------------    LOOP    -------------------------------------------------- //



void drawScreenMargins(){
  display.drawRect(0,0,128,64, WHITE);
}

bool AI = false;
bool ledBool = false;
bool lastLedBool = false;
bool pause = false;

void loop()
{

  bool P1_UP = false;
  bool P1_DOWN = false;
  bool P2_UP = false;
  bool P2_DOWN = false;


  P1_UP |= (digitalRead(UP_BUTTON_P1) == LOW);
  P1_DOWN |= (digitalRead(DOWN_BUTTON_P1) == LOW);
  P2_UP |= (digitalRead(UP_BUTTON_P2) == LOW);
  P2_DOWN |= (digitalRead(DOWN_BUTTON_P2) == LOW);
  ledBool |= (digitalRead(AI_BUTTON) == LOW);
  pause |= (digitalRead(PAUSE_BUTTON) == LOW);

  while(digitalRead(PAUSE_BUTTON) == LOW){delay(30);}
  
  while(pause){
    delay(100);
    if(digitalRead(PAUSE_BUTTON) == LOW){
      pause = false; while(digitalRead(PAUSE_BUTTON) == LOW){delay(30);}
    }
      
  }
  
  if(ledBool == true && lastLedBool == false){
    AI = true;
    digitalWrite(AI_LED, HIGH);
    lastLedBool = true;
    ledBool = false;
  }
  if(P1_UP ||P1_DOWN ||P2_UP || P2_DOWN){
    AI = false;
    digitalWrite(AI_LED, LOW);
    lastLedBool = false;
    ledBool = false;
  }
  
  
  if(ResetBall) //when game starts
  {
    ballX = random(60, 68);
    ballY = 32;

    do{ballDirX = random(-1,2);}
    while(ballDirX == 0);
    do{ballDirY = random(-1,2);}
    while(ballDirY == 0);

    ResetBall = false;
  }

  if(GameRunning) 
  {
    int updateX = ballX + ballDirX;
    int updateY = ballY + ballDirY;

    printIntermScore();
    drawScreenMargins();

    
    //-----------varificam daca mingea se loveste de peretele verical al playerului 2(stanga)
    if(updateX == 0)
    {
      player1Score++;
      if(player1Score < MAX_SCORE)  
        showScore(2);
      else endGame();    
    }
    //-----------varificam daca mingea se loveste de peretele verical al playerului 1 (dreapta)
    if(updateX == 128)
    {
      player2Score++;
      if(player2Score < MAX_SCORE)  
        showScore(1);
      else endGame();
    }
    //-------------------------hitting the horizontal walls
    if(updateY == 2 || updateY == 62 ){
      ballDirY *= -1;
    }
    //----------------------------------hitting the paddle 1
    if(updateX == paddle1X && updateY >=paddle1Y && updateY <= paddle1Y + PADDLE_SIZE ){
      ballDirX *= -1;
    }
       //----------------------------------hitting the paddle 2
    if(updateX == paddle2X && updateY >=paddle2Y && updateY <= paddle2Y + PADDLE_SIZE ){
      ballDirX *= -1;
    }

    display.drawPixel(ballX, ballY, WHITE);
    display.drawPixel(updateX, updateY, WHITE);

    ballX = updateX;
    ballY = updateY;
  }

  if(GameRunning && !AI) //paddle movement
  {
    int paddle1UpperY = paddle1Y + PADDLE_SIZE, paddle2UpperY = paddle2Y + PADDLE_SIZE;

    display.drawFastVLine(paddle1X, paddle1Y, PADDLE_SIZE, WHITE);
    display.drawFastVLine(paddle2X, paddle2Y, PADDLE_SIZE, WHITE);

    if(paddle1UpperY < 61){
      if(P1_DOWN) 
        paddle1Y++;
    }
    if(paddle1Y > 3){
      if(P1_UP) 
        paddle1Y--;
    }
    if(paddle2UpperY < 61){
      if(P2_DOWN) 
        paddle2Y++;
    }
    if(paddle2Y > 3){
      if(P2_UP) 
        paddle2Y--;
    }

    P1_UP = false;
    P1_DOWN = false;
    P2_UP = false;
    P2_DOWN = false;
  }
  else if(GameRunning && AI){
    int paddle1UpperY = paddle1Y + PADDLE_SIZE, paddle2UpperY = paddle2Y + PADDLE_SIZE;
    display.drawFastVLine(paddle1X, paddle1Y, PADDLE_SIZE, WHITE);
    display.drawFastVLine(paddle2X, paddle2Y, PADDLE_SIZE, WHITE);

    bool AI1_DOWN = false;
    bool AI1_UP = false;
    bool AI2_DOWN = false;
    bool AI2_UP = false;

    if( ballX > (SCREEN_WIDTH - 10) / 2){
      if((paddle1UpperY + paddle1Y)/2 > ballY){
        AI1_UP = true;
      }
      if((paddle1UpperY + paddle1Y)/2 < ballY){
        AI1_DOWN = true;
      }
    }
    else if( ballX < (SCREEN_WIDTH + 10) / 2){
      if((paddle2UpperY + paddle2Y)/2 > ballY){
        AI2_UP = true;
      }
      if((paddle2UpperY + paddle2Y)/2 < ballY){
        AI2_DOWN = true;
      }
    }

    if(paddle1UpperY < 61){
      if(AI1_DOWN) 
        paddle1Y++;
    }
    if(paddle1Y > 3){
      if(AI1_UP) 
        paddle1Y--;
    }
    if(paddle2UpperY < 61){
      if(AI2_DOWN) 
        paddle2Y++;
    }
    if(paddle2Y > 3){
      if(AI2_UP) 
        paddle2Y--;
    }
    
  }
  display.display();
  display.clearDisplay();
  
}
//--------------------------------------- CONTROLLER FUNCTIONS
boolean buttonsUnpressed()
{
  if(digitalRead(DOWN_BUTTON_P1) == HIGH && digitalRead(UP_BUTTON_P1) == HIGH && digitalRead(DOWN_BUTTON_P2) == HIGH && digitalRead(UP_BUTTON_P2) == HIGH)
  return true;
  else return false;
}
//--------------------------------------- GAME FUNCTIONS
bool blinkWall = false;

void showScore(int wall)
{
  ResetBall = true;
  display.clearDisplay();
  display.display();

  for(int i=0;i<20;i++){
    display.clearDisplay();
    //----------------------show score

    printNumberUtil(player2Score, 51, 26, 2);
    printStringUtil("-",61, 26, 2, WHITE);
    printNumberUtil(player1Score, 72, 26, 2);

    //----------------------
    delay(50);
    if(blinkWall){
      if(wall == 1)
        display.drawFastVLine(127, 0 , 63, WHITE);
      else if(wall == 2)
        display.drawFastVLine(0, 0 , 63, WHITE);
      blinkWall = false;
    }
    else{
      if(wall == 1)
        display.drawFastVLine(127, 0 , 63, BLACK);
      else if(wall == 2)
        display.drawFastVLine(0, 0 , 63, BLACK);
      blinkWall = true;
    }
    display.display();
  }

  blinkWall = false;
}

void printWinner(){
  if(player1Score > player2Score){
    delay(500);
    printStringUtil("PLAYER 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("LAYER 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("AYER 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("YER 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("ER 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("R 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil(" 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil(" WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("ON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("N", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("", 30, 20, 1, WHITE );
    delay(200);display.display();
  }
  else if(player1Score < player2Score){
    delay(500);
    printStringUtil("PLAYER 2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("LAYER 2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("AYER 2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("YER 2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("ER 2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("R 1 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil(" 2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("2 WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil(" WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("WON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("ON", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("N", 30, 20, 1, WHITE );
    delay(200);display.display();display.clearDisplay();
    printStringUtil("", 30, 20, 1, WHITE );
    delay(200);display.display();
  }
}
void endGame()
{
  
  blinkWall = false;

  for(int i=0;i<20;i++){
    display.clearDisplay();
    delay(50);
    if(blinkWall){
       printStringUtil("GAME OVER", 10, 20, 2, WHITE);
       blinkWall = false;
    }
    else{
       printStringUtil("GAME OVER", 10, 20, 2, BLACK);
       blinkWall = true;
    }
    display.display();
  }

  display.clearDisplay();
  printWinner();

  player1Score = 0;
  player2Score = 0;

  ResetBall = true;
  
  
}
