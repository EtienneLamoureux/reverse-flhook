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
	
	// FC04B166-0174636F-6465804C-65637225-74632F24-DA1B7365-6322A632
	char bytes[HCOORD_SIZE] = {-43, -1, -46, 5, 126, -22, -26, -82, 24, 23, -28, 37, -58, 96, -55, 118, -33, -30, 50, 118, 126, -17, 4, 84, 56, -27, -101, 114};
    for (const int& e : bytes) {
        std::cout << e << ", ";
    }
	
	char obuf[HCOORD_SIZE];
	obuf[0] = bytes[0];
	obuf[1] = bytes[1];
	for (uint i = 2, p = bytes[0] % set_scEncryptKey.length(); i < HCOORD_SIZE; i++, p++)
	{
		if (p > set_scEncryptKey.length())
			p = 0;
		
		std::cout << "\n" << "(" << i << "," << p << ") ";
		obuf[i] = (bytes[i] ^ set_scEncryptKey[p]);
		std::cout << (bytes[i] ^ set_scEncryptKey[p]);
	}
	
	std::cout << "\n";
    for (const int& e : obuf) {
        std::cout << e << ", ";
    }
	
	std::string sbuf;
	char buf[100];
	for (int i = 0; i < HCOORD_SIZE; i++)
	{
		if (i != 0 && (i % 4) == 0) sbuf += "-";
		snprintf(buf, sizeof(buf), "%02X", (char)obuf[i]);
		sbuf += buf;
	}
	
	std::cout << "\n" << sbuf;
}