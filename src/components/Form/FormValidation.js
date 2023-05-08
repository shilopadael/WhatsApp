

// validation function
function validateEmail(value, setEmailError) {
  const regex = /\S+@\S+\.\S+/;
  if(!regex.test(value)) {
    return false;
  }
  return true;
}


function validateDisplayName(value, setDisplayNameError) {
  const regex = /^[a-zA-Z0-9]+$/;
  if(!regex.test(value)) {
    return false;
  };
  return true;
}



function validate(email, password, confirmPassword, displayName) {
  let flag = true;
  if (!validateDisplayName(displayName)) {
    flag = false;
  }

  if (!validateEmail(email)) {
    flag = false;
  }

  if (!(password.length >= 8 && password.length <= 20)) {
    flag = false;
  }

  if (confirmPassword !== password) {
    flag = false;
  }
  return flag;
}

export {validate as default};