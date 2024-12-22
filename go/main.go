package main

import (
	"fmt"
	"strconv"
)

func main() {
	fmt.Println("Christ is King!")
	encoded := encode([]string{"Christ", "is", "King"})
	fmt.Println(encoded)
	fmt.Println(decode(encoded))
}

func top_k_frequent(nums []int, k int) []int {
	counts := make(map[int]int)
	for _, num := range nums {
		counts[num]++
	}
	buckets := make([][]int, len(nums)+1)
	for num, count := range counts {
		buckets[count] = append(buckets[count], num)
	}
	result := []int{}
	for i := len(nums); i >= 0; i-- {
		for _, num := range buckets[i] {
			result = append(result, num)
			if len(result) == k {
				return result
			}
		}
	}
	return result
}

func encode(strs []string) string {
	encoded := ""
	for _, str := range strs {
		str_length := strconv.Itoa(len(str))
		encoded = encoded + str_length + ";" + str
	}
	return encoded
}

func decode(str string) []string {
	i := 0
	decoded := []string{}
	for i < len(str) {
		str_size := ""
		for rune(str[i]) != ';' {
			str_size = str_size + string(str[i])
			i++
		}
		i++
		size, _ := strconv.Atoi(str_size)
		decoded = append(decoded, str[i:i+size])
		i += size
	}
	return decoded
}
