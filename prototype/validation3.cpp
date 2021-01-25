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
    
    #define HCOORD_SIZE 12
	
	char encrypted[HCOORD_SIZE] = {49, -101, 107, 118, -30, 80, 64, 34, 34, -71, 39, -95};
	char decrypted[HCOORD_SIZE] = {0, -84, 95, 70, 0, 0, 37, 67, 0, -128, 19, -59};
	
	char obuf[HCOORD_SIZE];
	for (uint i = 0; i < HCOORD_SIZE; i++)
	{
		obuf[i] = (encrypted[i] ^ decrypted[i]);
	}
	
	std::cout << "\n";
    for (const int& e : obuf) {
        std::cout << e << ", ";
    }
}