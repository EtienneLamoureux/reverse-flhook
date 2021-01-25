// string::operator[]
#include <iostream>
#include <string>
#include <iomanip>
#include <memory.h>

int main ()
{
    int num = 1;
if(*(char *)&num == 1)
{
    printf("Little-Endian\n");
}
else
{
    printf("Big-Endian\n");
}
    
    #define HCOORD_SIZE 28
	static std::string set_scEncryptKey = "secretcode";
	
	struct HYPERSPACE_COORDS
	{
		short parity;
		short seed;
		unsigned int system;
		float x;
		float y;
		float z;
		unsigned int time;
		float accuracy;
	};
	
	HYPERSPACE_COORDS coords;
		memset(&coords, 0, sizeof(coords));
		
	coords.parity =1276;
	coords.seed =5330;
    coords.system =100;
	coords.x =1.0;
	coords.y =2.0;
	coords.z =3.0;
	coords.time =7103l;
	coords.accuracy =25000.0;
	
	char c[HCOORD_SIZE];
    memcpy(c, &coords, HCOORD_SIZE);
    for (const int& e : c) {
    std::cout << e << ", ";
    }}
	