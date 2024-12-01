.PHONY: dev
dev:
	clj -M:repl/conjure

.PHONY: test
test:
	clj -M:test/cognitect

.PHONY: test-all
test-all:
	env ADVENT_TEST_ALL=true clj -M:test/cognitect
