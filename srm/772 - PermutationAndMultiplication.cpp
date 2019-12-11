#include <iostream>
#include <vector>

using namespace std;

class PermutationAndMultiplication {
public:
	int multiplyAndCount(int a, int b) {
		vector<int> v1;
		vector<int> v2;
		for (int i = 0; i < a; i++) v1.push_back(1);
		for (int i = 0; i < b; i++) v1.push_back(0);
		v2.push_back(1);
		for (int i = 0; i < b; i++) v2.push_back(0);
		for (int i = 0; i < a - 1; i++) v2.push_back(1);

		vector<int> v3((a + b) * 2 - 1);
		for (int i = 0; i < v1.size(); i++) v3[i] = v1[i];

		int p = 2 * a + b - 2;
		int t = 1;
		for (int i = 0; i < a - 1; i++)
			v3[p--] += t++;
		for (int i = 0; i < a - 1; i++)
			v3[p--] += --t;

		int result = 0;
		int temp = 0;
		for (int i = v3.size() - 1; i >= 0; i--) {
			result += (v3[i] % 2);
			if (i != 0)
				v3[i - 1] += (v3[i] / 2);
			else
				temp = v3[i] / 2;
		}

		while (temp != 0) {
			result += (temp % 2);
			temp /= 2;
		}

		return result;
	}
};