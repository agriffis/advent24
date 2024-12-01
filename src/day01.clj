(ns day01
  (:require [clojure.string :as str]
            [debux.core :refer [dbg]]
            [lib :refer :all]))

(defn parse
  [input]
  (->> (parse-longs input) (partition 2) (apply map vector)))

(defn part-a
  [input]
  (let [[left right] (parse input)]
    (reduce + (map (comp abs -) (sort left) (sort right)))))

(comment
  (part-a (read-example "day01"))
  (part-a (read-input "day01")))

(defn part-b
  [input]
  (let [[left right] (parse input)
        mults (frequencies right)]
    (reduce + (map #(* % (get mults % 0)) left))))

(comment
  (part-b (read-example "day01"))
  (part-b (read-input "day01")))
