#!/bin/bash

main() {
  local days=( src/day*.clj )
  local prev=${days[-1]//[^0-9]/}
  local next=${1:-$((${prev#0} + 1))}
  next=$(printf '%02d' ${next#0})

  if [[ ! -e src/day$next.clj ]]; then
    sed "s/day$prev/day$next/g" < src/day$prev.clj > src/day$next.clj
  fi

  mkdir -p resources/day$next
  vim src/day$next.clj \
    resources/day$next/{example,input}{,.part-a.out} \
    resources/day$next/{example,input}.part-b.out
}

main "$@"; exit
