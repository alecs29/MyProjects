
 
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
int ballX=64, ballY=32; 
int ballDirX=1, ballDirY=1; 

//----------------------------------paddles
int paddle1X = 8;
int paddle1Y = 16;

int paddle2X = 120;
int paddle2Y = 16;
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
  display.drawRect(0,0,128,64, WHITE);
  display.display();
//  while(millis() - start < 2000);
//  ballUpdate = millis();
//  paddleUpdate = ballUpdate;
  ballX = random(25, 65);
  ballY = random(3, 63);
  
}
//--------------------------------------------------    LOOP    -------------------------------------------------- //


void loop()
{
  display.clearDisplay();
  display.drawRect(0,0,128,64, WHITE);
  unsigned long time = millis();


  if(ResetBall)
  {
    ballX = random(13, 42);
    ballY = random(13, 42); 

  }

  if(GameRunning) 
  {
    uint8_t updateX = ballX + ballDirX;
    uint8_t updateY = ballY + ballDirY;
    //-----------varificam daca mingea se loveste de peretele vericat al playerului 2
    if(updateX == 0)
    {
      player1Score++;
      if(player1Score < MAX_SCORE)  
        showScore();
      else endGame();    
    }

    if(updateX == 95)
    {
      player2Score++;
      if(player2Score < MAX_SCORE)  
        showScore();
      else endGame();
    }

    display.drawPixel(ballX, ballY, WHITE);
    display.drawPixel(updateX, updateY, WHITE);
    display.drawFastVLine(paddle1X, paddle1Y, PADDLE_SIZE, WHITE);
    display.drawFastVLine(paddle2X, paddle2Y, PADDLE_SIZE, WHITE);

    
  }

  display.display();
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
  
}
