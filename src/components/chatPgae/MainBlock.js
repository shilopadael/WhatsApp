import imageOmer from '../../assests/images/Omer.png';
import imageShilo from '../../assests/images/shilo.jpg';
import LeftSide from './leftSide/LeftSide';
import RightSide from './rightSide/RightSide';




function MainBlock() {
  return (
    <>
      <div class="container border shadow container-lg container-md container-sm">
        <div class="row no-gutters h-100">
          <LeftSide />
          <RightSide />
        </div>
      </div>
    </>
  );
}

export default MainBlock;
