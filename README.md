# Clojure libpython-clj Interop Example

This project demonstrates how to use `libpython-clj` to interoperate between Clojure and Python, specifically focusing on integration with `uv` for dependency management.

## Source Code Overview (`src/`)

The `src` directory contains examples of different interop patterns:

- **`core.clj`**: Basic setup and initialization.
  - Shows how to initialize the Python environment.
  - Demonstrates a basic Python print call.
  - Demonstrates a simple calculation using the `numpy` library imported from Python.

- **`interop.clj`**: Data type conversions and library-specific interop.
  - **Basic Conversions**: Examples of converting Strings and Longs between Clojure and Python.
  - **Collections**: Converting Clojure vectors and maps to Python lists and dicts, and back again.
  - **Method Calling**: Demonstrates calling Python methods (e.g., `.sort()`) and built-in functions (e.g., `len()`).
  - **Keyword Arguments**: Using Clojure keywords to pass named arguments to Python functions.
  - **NumPy Interop**: Specific examples of creating NumPy arrays from Clojure data and performing operations like `np/mean`.

- **`bridging.clj`**: Advanced functional bridging and error handling.
  - **Function Passing**: Demonstrates passing Clojure functions (like `clj-square`) to Python functions like `map` and `filter`.
  - **Error Handling**: Shows how to catch Python exceptions (like `ModuleNotFoundError` or `ZeroDivisionError`) within Clojure using standard `try/catch` blocks.
  - **Safe Interop**: Brief example of using `try` for safe type conversion.

- **`python_interop.clj`**: Example of interoperating with a custom local Python library.
  - Demonstrates how to add the local project path to `sys.path`.
  - Shows how to `require-python` a local module (`python_lib.math_ops`).
  - Executes basic math operations defined in Python from Clojure.

## Python Library (`python_lib/`)

The `python_lib` directory contains custom Python code intended to be called from Clojure.

- **`math_ops.py`**: A simple Python module providing basic arithmetic functions (`add`, `subtract`).

## Custom Python Library Interop

To call local Python modules, the directory containing them must be in the Python search path (`sys.path`). `src/python_interop.clj` demonstrates this by:

1. Initializing Python: `(py/initialize!)`
2. Appending the current directory to `sys.path`:
   ```clojure
   (let [sys (py/import-module "sys")]
     (py/py. (py/get-attr sys "path") "append" "."))
   ```
3. Requiring the module: `(require-python '[python_lib.math_ops :as math])`

## Configuration

- **`deps.edn`**: Manages Clojure dependencies, including `libpython-clj` and `libpython-clj-uv`.
- **`resources/python.edn`**: Configures the Python environment, specifying the version, dependencies (like `numpy`), and the path to the virtual environment.
