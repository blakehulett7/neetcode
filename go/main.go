package main

import (
	"fmt"
	"strconv"
)

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

func product_except_self(nums []int) []int {
	result := make([]int, len(nums))
	left_product := 1
	for idx, num := range nums {
		result[idx] = left_product
		left_product *= num
	}
	right_product := 1
	for idx := len(nums) - 1; idx >= 0; idx-- {
		result[idx] *= right_product
		right_product *= nums[idx]
	}
	return result
}

func is_valid_sudoku(board [][]byte) bool {
	col_hashes := map[int]map[rune]bool{}
	grid_hashes := map[string]map[rune]bool{}
	for row := range board {
		row_hash := map[rune]bool{}

		for column := 0; column < 9; column++ {
			char := rune(board[row][column])
			if char == '.' {
				continue
			}

			seen_in_row := row_hash[char]
			if seen_in_row {
				return false
			}
			row_hash[char] = true

			seen_in_column := col_hashes[column][char]
			if seen_in_column {
				return false
			}
			_, exists := col_hashes[column]
			if !exists {
				col_hashes[column] = map[rune]bool{}
			}
			col_hashes[column][char] = true

			string_key := fmt.Sprintf("%d,%d", row/3, column/3)
			seen_in_grid := grid_hashes[string_key][char]
			if seen_in_grid {
				return false
			}
			_, exists = grid_hashes[string_key]
			if !exists {
				grid_hashes[string_key] = map[rune]bool{}
			}
			grid_hashes[string_key][char] = true
		}
	}
	return true
}
