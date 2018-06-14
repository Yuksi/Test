package main

import (
	"fmt"
	"encoding/json"
)

func findFibonacciNumberByIndex(number int) int {
	if number == 0 {
		return 0
	}
	if number == 1 || number == 2 {
		return 1
	}
	var fibo1 int = 1
	var fibo2 int = 1
	var fibonacci int = 1
	for i := 3; i<= number; i++ {
		fibonacci = fibo1 + fibo2
		fibo1 = fibo2
		fibo2 = fibonacci
	}
	return fibonacci
}

func printNumber(index int, number int) {
	mapD := map[int]int{index: number}
	mapB, _ := json.Marshal(mapD)
	fmt.Println(string(mapB))
}

func main() {
	var index int = 0
	var expected int
	var number int
	i := 0
	for index < 11 {
		fmt.Print("Enter next Fibonacci number (start with 0): ")
		fmt.Scan(&number)
		expected = findFibonacciNumberByIndex(index)
		if number != expected {
			printNumber(index, expected)
			i++
		}
		if i == 3 {
			break;
		}
		index++
	}
}
