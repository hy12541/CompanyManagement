import React from "react";
import _ from "lodash"; //前面的_也可以用 l, lodash来代替
import PropTypes from "prop-types"; //for data type checking

const Pagination = props => {
  const { itemsCount, pageSize, currentPage, onPageChange } = props; //object destructuring
  const pagesCount = Math.ceil(itemsCount / pageSize);
  if (pagesCount === 1) return null;
  //这里要先装一个 lodash.  npm i loadash@4.17.10
  //为了生成一个从1到n的array
  const pages = _.range(1, pagesCount + 1);
  return (
    //下面这些来自于bootstrap的pagination模板
    <nav>
      <ul className="pagination">
        {pages.map(page => (
          <li
            key={page}
            className={page === currentPage ? "page-item active" : "page-item"}
          >
            <button className="page-link" onClick={() => onPageChange(page)}>
              {page}
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
};
//active: 表示点的页码会加粗

Pagination.propTypes = {
  itemsCount: PropTypes.number.isRequired,
  pageSize: PropTypes.number.isRequired,
  currentPage: PropTypes.number.isRequired,
  onPageChange: PropTypes.func.isRequired
};
export default Pagination;
