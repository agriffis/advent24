(ns day02
  (:require [clojure.string :as str]
            [debux.core :refer [dbg]]
            [lib :refer :all]))

(defn parse
  [input]
  (->> input
       split-lines
       (mapv parse-longs)))

(defn safe-line?
  [line]
  (and (or (apply < line) (apply > line))
       (->> (partition 2 1 line)
            (map (partial apply (comp abs -)))
            (every? #(and (> % 0) (< % 4))))))

(defn part-a
  [input]
  (->> (parse input)
       (filter safe-line?)
       count))

(comment
  (part-a (read-example "day02"))
  (part-a (read-input "day02")))

(defn drop-each
  [v]
  (for [i (range 0 (count v))]
    (concat (subvec v 0 i) (subvec v (inc i)))))

(defn safeish-line?
  [line]
  (or (safe-line? line)
      (some safe-line? (drop-each line))))

(defn part-b
  [input]
  (->> (parse input)
       (filter safeish-line?)
       count))

(comment
  (part-b (read-example "day02"))
  (part-b (read-input "day02")))
