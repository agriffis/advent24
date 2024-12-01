(ns test
  (:require [clojure.java.classpath :as cp]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :refer :all])
  (:import [java.io File]))

(defn sort-u
  [xs]
  (sort (into #{} xs)))

(defn find-days
  "Find the day* namespace strings."
  []
  (->> (for [^File d (cp/classpath)
             :when (.isDirectory d)
             ^File f (.listFiles d)
             :when (and (.isFile f) (re-matches #"day\d+[.]clj" (.getName f)))]
         (-> (.getName f)
             (str/replace #"[.]clj$" "")))
       sort-u))

(defn find-outputs
  "Find the test output files for a given day."
  [day part all]
  (->> (for [^File cp (cp/classpath)
             :let [^File day-dir (io/file cp day)]
             :when (.isDirectory day-dir)
             ^File out-file (.listFiles day-dir)
             :let [out-name (.getName out-file)]
             :when (and (.endsWith out-name (str "." part ".out"))
                        (or all (not (.startsWith out-name "input"))))]
         out-file)))

(deftest days
  (let [all (some? (System/getenv "ADVENT_TEST_ALL"))]
    (doall (for [day (find-days)
                 part ["part-a" "part-b"]
                 out-file (find-outputs day part all)
                 :let [in-file (-> (str (.toPath out-file))
                                   (str/replace #"[.]part-.[.]out$" "")
                                   io/file)
                       input (slurp in-file)
                       output (str/trim-newline (slurp out-file))
                       part-fn (requiring-resolve (symbol (str day "/" part)))]]
             (testing (str day "/" part ": " (.getName in-file))
               (is (= (str (part-fn input)) output)))))))
