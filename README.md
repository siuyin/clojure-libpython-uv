# Clojure libpython-clj Interop Example

This project demonstrates how to use `libpython-clj` to interoperate between Clojure and Python, specifically focusing on integration with `uv` for dependency management.

## Source Code Overview (`src/`)

The `src` directory contains examples of different interop patterns:

- **`core.clj`**: Basic setup and initialization.
  - Shows how to initialize the Python environment.
  - Demonstrates a simple calculation using the `numpy` library imported from Python.

- **`bridging.clj`**: Advanced functional bridging and error handling.
  - **Function Passing**: Demonstrates passing Clojure functions (like `clj-square`) to Python functions like `map` and `filter`.
  - **Error Handling**: Shows how to catch Python exceptions (like `ModuleNotFoundError` or `ZeroDivisionError`) within Clojure using standard `try/catch` blocks.
  - **Safe Interop**: Brief example of using `try` for safe type conversion.

- **`interop.clj`**: Data type conversions and library-specific interop.
  - **Basic Conversions**: Examples of converting Strings and Longs between Clojure and Python.
  - **Collections**: Converting Clojure vectors and maps to Python lists and dicts, and back again.
  - **Method Calling**: Demonstrates calling Python methods (e.g., `.sort()`) and built-in functions (e.g., `len()`).
  - **Keyword Arguments**: Using Clojure keywords to pass named arguments to Python functions.
  - **NumPy Interop**: Specific examples of creating NumPy arrays from Clojure data and performing operations like `np/mean`.

## Configuration

- **`deps.edn`**: Manages Clojure dependencies, including `libpython-clj` and `libpython-clj-uv`.
- **`resources/python.edn`**: Configures the Python environment, specifying the version, dependencies (like `numpy`), and the path to the virtual environment.
