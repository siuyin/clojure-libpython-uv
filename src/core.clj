(ns core
  (:require [libpython-clj2.python :as py]))

(defn -main [& _args]
  (println "Initializing Python environment...")
  ;; libpython-clj-uv will run sync-python-setup! automatically if configured in python.edn
  ;; but we still need to initialize libpython-clj itself.
  (py/initialize!)
  
  (println "Python initialized successfully.")
  (py/run-simple-string "print('hello world from python!')")
  (let [np (py/import-module "numpy")]
    (println "Numpy version:" (py/get-attr np "__version__"))
    (println "Simple calculation (1 + 1):" (py/call-attr np "add" 1 1))))
