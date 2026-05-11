(ns interop
  (:require [libpython-clj2.python :as py]
            [libpython-clj2.require :refer [require-python]]))

;; We can also use require-python to import modules similarly to Clojure namespaces
(require-python '[builtins :as python]
                '[numpy :as np])

(defn -main [& args]
  (py/initialize!)

  (println "=== 1. Basic Type Conversions ===")
  (let [clj-str "Hello"
        py-str (py/->python clj-str)
        clj-long 42
        py-long (py/->python clj-long)]
    (println "Clojure String:" clj-str "->" "Python Type:" (py/python-type py-str))
    (println "Python String content (converted to Clojure):" (py/->jvm py-str))
    (println "Clojure Long:" clj-long "->" "Python Type:" (py/python-type py-long))
    (println "Python Long content (converted to Clojure):" (py/->jvm py-long)))

  (println "\n=== 2. Collection Conversions ===")
  (let [clj-vec [1 2 3]
        py-list (py/->python clj-vec)
        clj-map {:a 1 :b 2}
        py-dict (py/->python clj-map)]
    (println "Clojure Vector:" clj-vec "->" "Python List:" py-list)
    (println "Clojure Map:" clj-map "->" "Python Dict:" py-dict)
    ;; Convert back
    (println "Back to Clojure Vector:" (py/->jvm py-list))
    (println "Back to Clojure Map:" (py/->jvm py-dict)))

  (println "\n=== 3. Calling Python Functions & Methods ===")
  (let [my-list (py/->py-list [3 1 2])]
    ;; Calling a built-in function
    (println "Length via builtins/len:" (python/len my-list))

    ;; Calling a method on a Python object
    (py/call-attr my-list "sort")
    (println "Sorted list (mutated in place):" (py/->jvm my-list)))

  (println "\n=== 4. Keyword Arguments ===")
  ;; Python: sorted([3, 1, 2], reverse=True)
  (let [unsorted [3 1 2]
        sorted-rev (python/sorted unsorted :reverse true)]
    (println "Sorted reverse with kw-args:" (py/->jvm sorted-rev)))

  (println "\n=== 5. NumPy Interop ===")
  (let [data [1.0 2.0 3.0]
        np-array (np/array data)]
    (println "NumPy Array from Clojure:" np-array)
    (println "NumPy Mean:" (np/mean np-array))
    ;; Converting NumPy array back to Clojure often results in a sequence or tech.v3.dataset-like object
    ;; Depending on the version and configuration.
    (println "NumPy Array back to Clojure:" (py/->jvm np-array))))
