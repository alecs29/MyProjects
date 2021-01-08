#include <U8glib.h>
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

// Declaration for SSD1306 display connected using software SPI (default case):
#define OLED_MOSI   9 //d1
#define OLED_CLK   10 //d0
#define OLED_DC    12
#define OLED_CS    11
#define OLED_RESET 13
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT,
  OLED_MOSI, OLED_CLK, OLED_DC, OLED_RESET, OLED_CS);
  
U8GLIB_SSD1306_128X64 u8g(6, 2, U8G_PIN_NONE, 4 , 5 );
#define RED 0xF800

/**
 * Made with Marlin Bitmap Converter
 * https://marlinfw.org/tools/u8glib/converter.html
 * This bitmap from the file 'arduinopongTExt.jpg'
 */
//------------------------------------------ GAME CONSTANTS
const int MAX_SCORE = 10;
const int PADDLE_SPEED = 10;
const int PADDLE_SIZE = 18;
const int BALL_SPEED = 10;

//------------------------------------------- GAME VARIABLES
//----------------------------------players
int player1Score, player2Score = 0; 

//----------------------------------ball
unsigned int ballX=64, ballY=32; 
unsigned int ballDirX=1, ballDirY=1; 

//long ballUpdate;

//----------------------------------paddles
unsigned int paddle2X = 8;
unsigned int paddle2Y = 16;

unsigned int paddle1X = 120;
unsigned int paddle1Y = 16;
//long paddleUpdate;

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

  display.setCursor(21,20);
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
  delay(2000);
  
}
void printNumber(int nr, int x, int y)                                                      //####
{
  display.setCursor(x,y);
  display.setTextColor(WHITE);
  display.setTextSize(1);
  display.setTextWrap(false);
  display.print(nr);                                                       
}
void printBallCoords()                                                      //####
{
  display.setCursor(5, 2);
  display.setTextColor(WHITE);
  display.setTextSize(1);
  display.setTextWrap(false);
  display.print(ballX);  
  display.setCursor(32, 2);  
  display.print(ballY);                                                     
}


//--------------------------------------------------    SETUP    -------------------------------------------------- //
void setup()
{
  pinMode(UP_BUTTON_P1, INPUT_PULLUP);
  pinMode(DOWN_BUTTON_P1, INPUT_PULLUP); 
  pinMode(UP_BUTTON_P2, INPUT_PULLUP);
  pinMode(DOWN_BUTTON_P2, INPUT_PULLUP); 

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
    //Serial.println(DOWN_BUTTON_P1);
    delay(100);
  }
    unsigned long start = millis();

  delay(500);
  
  display.clearDisplay();
  drawScreenMargins();
  display.display();

  ballX = random(30, 70);
  ballY = random(20, 30); 
  
}
//--------------------------------------------------    LOOP    -------------------------------------------------- //



void drawScreenMargins(){
  display.drawRect(0,0,128,64, WHITE);
}

void loop()
{
  //delay(100);
//  display.clearDisplay();

  static bool P1_UP = false;
  static bool P1_DOWN = false;
  static bool P2_UP = false;
  static bool P2_DOWN = false;

  P1_UP |= (digitalRead(UP_BUTTON_P1) == LOW);
  P1_DOWN |= (digitalRead(DOWN_BUTTON_P1) == LOW);
  P2_UP |= (digitalRead(UP_BUTTON_P2) == LOW);
  P2_DOWN |= (digitalRead(DOWN_BUTTON_P2) == LOW);
  
  drawScreenMargins();
  if(ResetBall) //when game starts
  {
    ballX = random(30, 70);
    ballY = random(20, 30); 

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

    printBallCoords();

    //printNumber(ballDirX, 56, 26);
    //printNumber(ballDirY, 64, 26);
    
    //-----------varificam daca mingea se loveste de peretele vericat al playerului 2(stanga)
    if(updateX == 0)
    {
      player1Score++;
      if(player1Score < MAX_SCORE)  
        showScore();
      else endGame();    
    }
    //-----------varificam daca mingea se loveste de peretele vericat al playerului 1 (dreapta)
    if(updateX == 128)
    {
      player2Score++;
      if(player2Score < MAX_SCORE)  
        showScore();
      else endGame();
    }
    //-------------------------hitting the horizontal walls
    if(updateY == 2 || updateY == 62 ){
      ballDirY *= -1;
      printNumber(ballDirY, 108, 2);
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

    //ballUpdate += BALL_SPEED;
    ballX = updateX;
    ballY = updateY;
  }

  if(GameRunning) //paddle movement
  {
    int paddle1Uppery = paddle1Y + PADDLE_SIZE, paddle2Uppery = paddle2Y + PADDLE_SIZE;
    //paddleUpdate += PADDLE_SIZE;
    display.drawFastVLine(paddle1X, paddle1Y, PADDLE_SIZE, WHITE);
    display.drawFastVLine(paddle2X, paddle2Y, PADDLE_SIZE, WHITE);

    if(paddle1Uppery < 61){
      if(P1_DOWN) 
        paddle1Y++;
    }
    if(paddle1Y > 3){
      if(P1_UP) 
        paddle1Y--;
    }
    if(paddle2Uppery < 61){
      if(P2_UP) 
        paddle2Y--;
    }
    if(paddle2Y > 1){
      if(P2_DOWN) 
        paddle2Y++;
    }

    P1_UP = false;
    P1_DOWN = false;
    P2_UP = false;
    P2_DOWN = false;
  }
  display.display();
  display.clearDisplay();
  
}
//--------------------------------------- CONTROLLER FUNCTIONS
boolean buttonsUnpressed()
{
  if(digitalRead(DOWN_BUTTON_P1) == HIGH && digitalRead(UP_BUTTON_P1) == HIGH)
  return true;
  else return false;
}
//--------------------------------------- GAME FUNCTIONS
void showScore()
{
  
}
void endGame()
{
  display.setCursor(10,20);
  display.setTextColor(WHITE);
  display.setTextSize(2);
  display.setTextWrap(false);
  display.print("GAME  OVER");
  display.display();                                                        
  display.clearDisplay();
  delay(3000);
}
