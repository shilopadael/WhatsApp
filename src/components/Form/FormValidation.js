

// validation function
function validateUsername(value) {
  const regex = /^\w{3,12}$/
  if(!regex.test(value)) {
    return false;
  }
  return true;
}


function validateDisplayName(value) {
  const regex = /^[a-zA-Z0-9\s]+$/;
  if(!regex.test(value)) {
    return false;
  };
  return true;
}



function validate(username, password, confirmPassword, displayName) {
  let flag = true;
  if (!validateDisplayName(displayName)) {
    flag = false;
  }

  if (!validateUsername(username)) {
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