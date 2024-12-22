package main

import "fmt"

func main() {
	fmt.Println("Christ is King!")
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
