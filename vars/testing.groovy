def call(Map input) {
  // Validation
  def requiredFlags = [ 'file', 'dir' ]
  requiredFlags.each { flag ->
    if (!input[flag]) {
      throw new IllegalArgumentException("${flag} was not specified")
    }
  }
  print(input.file)
  print(input.dir)
}

//How to use this method?
//def testing(file: 'docx', dir: 'directory')
