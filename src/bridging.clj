(ns bridging
  (:require [libpython-clj2.python :as py]
            [libpython-clj2.require :refer [require-python]]))

(require-python '[builtins :as python])

(defn clj-square [x]
  (println "  [Clojure] Squaring:" x)
  (* x x))

(defn -main [& _args]
  (py/initialize!)

  (println "=== 1. Passing Clojure Functions to Python ===")
  (let [numbers [1 2 3 4]
        ;; Python's map returns an iterator, we convert it to a list in Python
        ;; before bringing it back to Clojure
        results (python/list (python/map clj-square numbers))]
    (println "Results from python/map (as Clojure list):" (py/->jvm results)))

  (println "\n=== 2. Clojure Function as Callback (Filter) ===")
  (let [numbers [10 20 30 40 50]
        is-large? (fn [x] (> x 25))
        filtered (python/list (python/filter is-large? numbers))]
    (println "Filtered results (x > 25):" (py/->jvm filtered)))

  (println "\n=== 3. Error Handling (Python Exception) ===")
  (try
    ;; Attempting to import a non-existent module
    (py/import-module "this_module_does_not_exist")
    (catch Exception e
      (println "Caught expected exception:" (.getMessage e))))

  (println "\n=== 4. Error Handling (ZeroDivisionError) ===")
  (try
    (python/eval "1 / 0")
    (catch Exception e
      (println "Caught ZeroDivisionError:" (.getMessage e))))

  (println "\n=== 5. Safe Interop with try-python ===")
  ;; libpython-clj provides some safety utilities, but standard try/catch is usually sufficient
  ;; for catching Python exceptions as they are mapped to JVM exceptions.
  (let [safe-result (try
                      (python/int "not_a_number")
                      (catch Exception _
                        :error-occurred))]
    (println "Handled error with return value:" safe-result)))
