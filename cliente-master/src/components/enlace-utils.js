export function getImageSrcGenerator(poster) {
  if (poster) {
    return function(enlace) {
      if (!enlace.hasImage) return "/placeholder_large.png";
      return `http://localhost:8080/api/enlaces/${enlace.id}/image`;
    };
  } else {
    return function(enlace) {
      if (!enlace.hasImage) return "/placeholder_small.png";
      return `http://localhost:8080/api/enlaces/${enlace.id}/image`;
    };
  }
}
