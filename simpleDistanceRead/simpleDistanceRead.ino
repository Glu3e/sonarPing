//Enviromental Variables

long sensorsum = 0;
int n = 1;
int mean = 0;
int lastmean = 0;

void setup() {
  
  pinMode(13,OUTPUT);
  Serial.begin(9600);

}

void loop() {
  
  int sensorValue = analogRead(A0);
  
  
  // Calculate mean of measures of a specified distance 
  // in order to smooth the results and after create the regression line for distance
  sensorsum = (sensorsum + sensorValue);
  mean = (sensorsum / n);
  n = n + 1;
  
  if(n > 500){
      digitalWrite(13,HIGH);
      delay(250);
      digitalWrite(13,LOW);
      Serial.println("--------RESET--------");
     
      n = 1;
      sensorsum = 0;
      lastmean = mean; 
  }
  
  
  Serial.print("Mean = ");
  Serial.print(mean);
  Serial.print("Last Mean =");
  Serial.println(lastmean);
  delay(25);
  
  

}
