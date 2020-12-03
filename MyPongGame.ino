#include <Adafruit_SSD1331.h>
#include <Adafruit_GFX.h>
#include <Adafruit_GrayOLED.h>
#include <Adafruit_SPITFT.h>
#include <Adafruit_SPITFT_Macros.h>
#include <gfxfont.h>
#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 11, 6, 5, 4, 3);

Adafruit_SSD1331 display = Adafruit_SSD1331();

#define SD_CS 4 // SD card select pin
#define TFT_CS 10 // TFT select pin
#define TFT_DC 9 // TFT display/command pin

#define BLACK 0x0000
#define BLUE 0x001F
#define RED 0xF800
#define GREEN 0x07E0
#define CYAN 0x07FF
#define MAGENTA 0xF81F
#define YELLOW 0xFFE0
#define WHITE 0xFFFF

#define UP_BUTTON 9
#define DOWN_BUTTON 10

int SCORE = 0;
int PLAYER_SCORE = 0;

byte ball[8] = {
 B00000,
 B00100,
 B01110,
 B11111,
 B01110,
 B00100,
 B00000,
};

byte line [8] = {
 B00000,
 B00100,
 B00100,
 B00100,
 B00100,
 B00100,
 B00000,
};

void setup()
{
  pinMode(UP_BUTTON, INPUT_PULLUP);
  pinMode(DOWN_BUTTON, INPUT_PULLUP);  
  display.begin();
  display.fillScreen(0x0000);

  display.setCursor(8, 8);
  display.setTextSize(8);
}

void loop()
{
}
