(ns python-interop
  (:require [libpython-clj2.python :as py]
            [libpython-clj2.require :refer [require-python]]))

;; Initialize Python and update sys.path so we can require local modules
(py/initialize!)
(let [sys (py/import-module "sys")]
  (py/py. (py/get-attr sys "path") "append" "."))

(require-python '[python_lib.math_ops :as math])

(defn -main [& _args]
  (let [a 10
        b 5]
    (println "=== Calling Python Math Library ===")
    (println (format "Adding %d + %d = %s" a b (math/add a b)))
    (println (format "Subtracting %d - %d = %s" a b (math/subtract a b)))))
